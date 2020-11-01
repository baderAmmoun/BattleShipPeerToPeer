package Controller;

import Model.Coordinate;
import Model.Fleet;
import Model.Ship;
import Network.BattleShipProtocol;
import Network.HandleRespond;
import Network.Request;
import Network.Respond;
import View.BattleViewClassic;

import java.util.Map;

public class CounterReceiver extends BattleShipProtocol {

    public static void registerTowerControl(TowerControl towerControl){
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
            respond.setTargetHit(true);
        }
        else
            respond.setTargetHit(false);

        respond.setCountOfNeighborShip(fleet.countOfNeighborShip(coordinate));
    }

    @Override
    public void handleResultOfStrike(Respond respond) {


        //this.handleRespond.isDestroyed(respond);
       // this.handleRespond.numOfNeighborShip(respond);
        BattleViewClassic.getInstance().NumNeighborShips(respond.getCountOfNeighborShip(),respond.getX(),respond.getY());
        System.out.println(respond.isTargetHit());
        System.out.println("and the number of neighbor ships are"+respond.getCountOfNeighborShip());
    }
}
