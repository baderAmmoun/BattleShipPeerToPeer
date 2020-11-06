package Controller;

import Model.Fleet;
import Network.ConnectionManager;
import Network.Request;

public class ExecuteStrike {
    private static int numOfCount;

    public void attack(int xCoordinate,int yCoordinate){
        if(numOfCount>=Fleet.getFleet().limitOfCounters())
            return;
        BattleShipProtocol.increaseLocalAttempt();
        ConnectionManager.getConnectionManger().sendMessage(new Request(xCoordinate,yCoordinate,"feras","bader"),888);
        numOfCount++;
    }

}
