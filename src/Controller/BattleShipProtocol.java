package Controller;

import Model.ClassicRoles;
import Model.Coordinate;
import Model.Fleet;
import Model.Ship;
import Network.AbstractBattleShipProtocol;
import Network.ConnectionManager;
import Network.Request;
import Network.Respond;
import View.BattleViewClassic;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class BattleShipProtocol extends AbstractBattleShipProtocol {

    public static void registerTowerControl(TowerControl towerControl){
        Fleet fleet= Fleet.getFleet();
        fleet.registerEnemiesTower(towerControl);
        fleet.registerRoles(new ClassicRoles());
    }

    @Override
    public void handleStrikeRequest(Request request,Respond respond) {
       System.out.println("here by the real implementation of the handle request ");
        Fleet fleet=Fleet.getFleet();
        fleet.numRemoteAttack();
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
        if(Fleet.getFleet().isGameEnd()){
            BattleViewClassic.getInstance().EndGame(Fleet.getFleet().amIWin());
            respond.setGameEnd(true);
        }
    }

    @Override
    public void handleResultOfStrike(Respond respond) {
        BattleViewClassic panel = BattleViewClassic.getInstance();
        panel.NumNeighborShips(respond.getCountOfNeighborShip(), respond.getX(), respond.getY());
        panel.increaseDestroyedShips(respond.isTargetHit());
        if (respond.isTargetHit()) {
            Fleet.getFleet().destroyRemotedShips();
        }

    }

    @Override
    public void startGame(Request request) {
     Fleet fleet=Fleet.getFleet();
     fleet.riseRemoteReadiness();
     if(fleet.isGameStart()) {
         BattleViewClassic.getInstance().startGame();
         System.out.println("I will disable the panel");
     }
    }

    @Override
    public void endGame(Respond respond) {
       Socket socket= ConnectionManager.getConnectionManger().getSocket(respond.getSenderPlayer());
        try {
            System.out.println("I will delete the socket");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
