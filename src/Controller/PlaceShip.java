package Controller;

import Configuration.Config;
import Model.Coordinate;
import Model.Fleet;
import Model.Ship;
import Network.ConnectionManager;
import Network.Request;

public class PlaceShip {

    private Fleet fleet;

    public PlaceShip(){
        this.fleet=Fleet.getFleet();

    }

    public void registerTower(TowerControl towerControl){

       fleet.registerAlliesTowers(towerControl);
    }

    public void placeShip(int xCoordinate, int yCoordinate){
        System.out.println("I will plcae one ship");
        if(fleet.isLocalReady())
            return;
        Coordinate coordinate=new Coordinate(xCoordinate,yCoordinate);
        Ship ship=new Ship(coordinate);

         this.fleet.addShip(ship);
        if (this.fleet.isLocalReady()){
            Config config= null;
            try {
                config = Config.getConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int remotePort=Integer.parseInt(config.getValue("remotePort"));
            String sender=config.getValue("sender");
            String receiver=config.getValue("receiver");
            Request request=new Request(-1,-1,sender,receiver);
            ConnectionManager.getConnectionManger().sendMessage(request,remotePort);

        }


    }
}
