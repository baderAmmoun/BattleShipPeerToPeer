package Network;

public class Respond extends  Request {

    private boolean isMissed;
    public Respond(int x, int y, String SenderPlayer,String RecieverPlayer) {
        super(x, y,SenderPlayer,RecieverPlayer);
    }

    public boolean isMissed() {
        return isMissed;
    }

    public void setMissed(boolean missed) {
        isMissed = missed;
    }
}
