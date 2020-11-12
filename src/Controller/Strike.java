package Controller;

import Configuration.Config;
import Model.Fleet;
import Network.ConnectionManager;
import Network.Request;
import View.BattleShipButton;
import View.BattleViewClassic;

public class Strike {
    private static int numOfCount;

    public Strike(BattleShipButton ship) {

        if(numOfCount>=Fleet.getFleet().limitOfCounters())
            return;
        Fleet.getFleet().increaseLocalAttempt();
        if(Fleet.getFleet().isGameEnd()){
            BattleViewClassic.getInstance().EndGame(Fleet.getFleet().amIWin());
            System.out.println("the game is end");
        }
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
        ConnectionManager.getConnectionManger().sendMessage(new Request(ship.getXCoordinate(),ship.getYCoordinate(),sender,receiver),remotePort);
        numOfCount++;
    }


}
