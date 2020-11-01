package Network;

public class Respond extends ExchangeableMessage {

    private boolean isTargetHit;
    public Respond(int x, int y, String senderPlayer,String receiverPlayer) {
        super(x, y,senderPlayer,receiverPlayer);
    }

    public boolean isTargetHit() {
        return isTargetHit;
    }

    public void setTargetHit(boolean targetHit) {
        isTargetHit = targetHit;
    }
}
