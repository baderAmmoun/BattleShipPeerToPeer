package Controller;

import Model.*;
import Network.AbstractBattleShipProtocol;
import Network.ConnectionManager;
import Network.Request;
import Network.Respond;
import View.BattleViewClassic;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class BattleShipProtocol extends AbstractBattleShipProtocol {

    public static void registerTowerControl(TowerControl towerControl) {
        Fleet fleet = Fleet.getFleet();
        fleet.registerEnemiesTower(towerControl);

    }

    @Override
    public void handleStrikeRequest(Request request, Respond respond) {
        Fleet fleet = Fleet.getFleet();
        Referee referee=Referee.getInstance();
        referee.numRemoteAttack();
        Coordinate coordinate = new Coordinate(request.getX(), request.getY());
        Map<Boolean, Ship> map = fleet.isShipThere(coordinate);
        if (map.containsKey(true)) {
            fleet.beingAttacked(map.get(true));
            fleet.notifyEnemiesTowers("black", request.getX(), request.getY());
            respond.setTargetHit(true);
            referee.disLocalShip();
        } else
            respond.setTargetHit(false);

        respond.setCountOfNeighborShip(fleet.countOfNeighborShip(coordinate));
        if (referee.isGameEnd()) {
            BattleViewClassic.getInstance().EndGame(referee.amIWin());
            respond.setGameEnd(true);
        }
    }

    @Override
    public void handleResultOfStrike(Respond respond) {
        BattleViewClassic panel = BattleViewClassic.getInstance();
        panel.NumNeighborShips(respond.getCountOfNeighborShip(), respond.getX(), respond.getY());
        panel.increaseDestroyedShips(respond.isTargetHit());
        if (respond.isTargetHit()) {
           Referee.getInstance().destroyRemotedShips();
        }

    }

    @Override
    public void startGame(Request request) {
        //Fleet fleet = Fleet.getFleet();
        Referee referee=Referee.getInstance();
        referee.riseRemoteReadiness();
        if (referee.isGameStart()) {
            BattleViewClassic.getInstance().startGame();
        }
    }

    @Override
    public void endGame(Respond respond) {
        Socket socket = ConnectionManager.getConnectionManger().getSocket(respond.getSenderPlayer());
        try {
            System.out.println("I will delete the socket");
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


