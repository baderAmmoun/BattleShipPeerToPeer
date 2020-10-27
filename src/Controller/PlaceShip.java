package Controller;

import Model.Coordinate;
import Model.Fleet;
import Model.Ship;

public class PlaceShip {

    private Fleet fleet;
    private TowerControl control;
    public PlaceShip(){
        this.fleet=Fleet.getFleet();

    }

    public void registerTower(TowerControl towerControl){
        this.control=towerControl;
    }

    public void placeShipe(int xcordinate,int yCoordinate){

        Coordinate coordinate=new Coordinate(xcordinate,yCoordinate);
        Ship ship=new Ship(coordinate);
        this.fleet.addShip(ship);
        this.control.OnAction();
       // this.control.changeColor("blue");


    }
}
