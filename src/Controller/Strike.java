package Controller;

import Configuration.Config;
import Model.Fleet;
import Network.ConnectionManager;
import Network.Request;
import View.BattleShipButton;

public class Strike {
    private static int numOfCount;

    public Strike(BattleShipButton ship) {

        if(numOfCount>=Fleet.getFleet().limitOfCounters())
            return;
        BattleShipProtocol.increaseLocalAttempt();
        Config config= null;
        try {
            config = Config.getConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int port=Integer.parseInt(config.getValue("port"));
        int remotePort=Integer.parseInt(config.getValue("remotePort"));
        String sender=config.getValue("sender");
        String receiver=config.getValue("receiver");
        System.out.println("this line has been added in the second instance");
        System.out.println("the remote port of the port :"+port+":"+remotePort);
        ConnectionManager.getConnectionManger().sendMessage(new Request(ship.getXcoordinate(),ship.getyCoordinate(),sender,receiver),remotePort);

        numOfCount++;
    }


}
