package Controller;

import Model.Attack;
import Model.Coordinate;
import Model.Fleet;
import Model.Ship;
import Network.BattleShipProtocol;
import Network.EndPoint;
import Network.Request;
import Network.Respond;

import java.util.Map;

public class CounterReciever extends BattleShipProtocol {

    public static void registerTowerControll(TowerControl towerControl){
        Fleet.getFleet().registerEnemiesTower(towerControl);
    }
    @Override
    public void handleStrikeRequest(Request request,Respond respond) {
       System.out.println("here by the real implementation of the handle request ");
        Fleet fleet=Fleet.getFleet();
        Coordinate coordinate=new Coordinate(request.getX(),request.getY());
        Map<Boolean, Ship> map=fleet.isShipThere(coordinate);
        if(map.containsKey(true)){
            fleet.beingAttacked(map.get(true));
            fleet.notifyEnemiesTowers("black", request.getX(), request.getY());
            respond.setMissed(false);
        }
        else
            respond.setMissed(true);
    }

    @Override
    public void handleResultOfStrike(Respond respond) {
       System.out.println(respond.isMissed());
    }
}
