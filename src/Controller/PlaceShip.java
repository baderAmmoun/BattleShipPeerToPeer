package Controller;

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

        Coordinate coordinate=new Coordinate(xCoordinate,yCoordinate);
        Ship ship=new Ship(coordinate);
        this.fleet.addShip(ship);
        if (this.fleet.isLocalIsReady()){
            Request request=new Request(-1,-1,"feras","bader");
            ConnectionManager.getConnectionManger().sendMessage(request,888);

        }


    }
}
