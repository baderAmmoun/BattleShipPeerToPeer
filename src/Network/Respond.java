package Network;

public class Respond extends  Request {

    private boolean isTargetHit;
    public Respond(int x, int y, String SenderPlayer,String RecieverPlayer) {
        super(x, y,SenderPlayer,RecieverPlayer);
    }

    public boolean isTargetHit() {
        return isTargetHit;
    }

    public void setTargetHit(boolean targetHit) {
        isTargetHit = targetHit;
    }
}
