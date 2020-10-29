package Network;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 4L;
    private final int x;
    private final int y;
    private final String SenderPlayer;
    private final String ReceiverPlayer;
    public Request(int x, int y, String SenderPlayer,String ReceiverPlayer) {
        this.x = x;
        this.y = y;
        this.SenderPlayer = SenderPlayer;
        this.ReceiverPlayer=ReceiverPlayer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSenderPlayer() {
        return SenderPlayer;
    }

    public String getReceiverPlayer() {
        return ReceiverPlayer;
    }
}

