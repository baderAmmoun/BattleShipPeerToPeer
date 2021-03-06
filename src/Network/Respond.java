package Network;


public class Respond extends ExchangeableMessage {
    private int countOfNeighborShip;
    private boolean isTargetHit;
    private boolean isGameEnd=false;
    public Respond(int x, int y, String senderPlayer,String receiverPlayer) {
        super(x, y,senderPlayer,receiverPlayer);
    }

    public boolean isTargetHit() {
        return isTargetHit;
    }

    public void setTargetHit(boolean targetHit) {
        isTargetHit = targetHit;
    }

    public int getCountOfNeighborShip() {
        return countOfNeighborShip;
    }

    public void setCountOfNeighborShip(int countOfNeighborShip) {
        this.countOfNeighborShip = countOfNeighborShip;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        isGameEnd = gameEnd;
    }
}
