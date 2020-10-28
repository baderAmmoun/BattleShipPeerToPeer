package Controller;

import Model.Attack;
import Model.Coordinate;
import Model.Fleet;
import Model.Ship;
import Network.EndPoint;

import java.util.Map;

public class CounterReciever implements EndPoint {

    public static void registerTowerControll(TowerControl towerControl){
        Fleet.getFleet().registerEnemiesTower(towerControl);
    }

    @Override
    public String onCommand(Attack attack) {
        Fleet fleet=Fleet.getFleet();
        Coordinate coordinate=new Coordinate(attack.getX(),attack.getY());
        Map<Boolean, Ship> map=fleet.isShipThere(coordinate);
        if(map.containsKey(true)){
            fleet.beingAttacked(map.get(true));
            fleet.notifyEnemiesTowers("black", attack.getX(), attack.getY());
        }

        return "fff";
    }

    @Override
    public void onAction(String s) {

    }
}
