package Network;

<<<<<<< HEAD
public class Respond extends ExchangeableMessage{

    private boolean isTargetHit;
    public Respond(int x, int y, String senderPlayer,String recieverPlayer) {

        super(x, y,senderPlayer,recieverPlayer);
=======
public class Respond extends ExchangeableMessage {

    private boolean isTargetHit;
    public Respond(int x, int y, String senderPlayer,String receiverPlayer) {
        super(x, y,senderPlayer,receiverPlayer);
>>>>>>> master
    }

    public boolean isTargetHit() {
        return isTargetHit;
    }

    public void setTargetHit(boolean targetHit) {
        isTargetHit = targetHit;
    }
}
