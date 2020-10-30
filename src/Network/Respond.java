package Network;

public class Respond extends ExchangeableMessage{

    private boolean isTargetHit;
    public Respond(int x, int y, String senderPlayer,String recieverPlayer) {

        super(x, y,senderPlayer,recieverPlayer);
    }

    public boolean isTargetHit() {
        return isTargetHit;
    }

    public void setTargetHit(boolean targetHit) {
        isTargetHit = targetHit;
    }
}
