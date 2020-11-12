package Controller;

import Configuration.Config;
import Model.*;
import Network.ConnectionManager;
import Network.Request;

import java.util.Map;

public class PlaceShip {

    private Fleet fleet;
    private Referee referee;

    public PlaceShip(){
        this.fleet=Fleet.getFleet();
        this.referee=Referee.getInstance();
        this.referee.registerRoles(new ClassicRoles());

    }

    public void registerTower(TowerControl towerControl){

       fleet.registerAlliesTowers(towerControl);

    }
    public void registerAudience(Audience audience){

        referee.registerAudience(audience);

    }

    public void placeShip(int xCoordinate, int yCoordinate){
        if(referee.isLocalReady())
            return;
        Coordinate coordinate=new Coordinate(xCoordinate,yCoordinate);

        Map<Boolean,Ship> map=fleet.isShipThere(coordinate);
        if(map.containsKey(true)){
            Ship ship =map.get(true);
            fleet.removeShip(ship);
            referee.retreatShip();
            return;
        }
        Ship ship=new Ship(coordinate);

         this.fleet.addShip(ship);
         this.referee.placeShip();
         if(this.referee.startGame()){
             this.referee.notifyOnStartAudience();
         }
        if (this.referee.isLocalReady()){
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
