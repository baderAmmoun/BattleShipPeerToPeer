package Network;

import java.io.Serializable;

public abstract class ExchangeableMessage implements Serializable {
    private static final long serialVersionUID = 4L;
    private final int x;
    private final int y;
    private final String senderPlayer;
    private final String receiverPlayer;
    public ExchangeableMessage(int x, int y,String senderPlayer,
                               String receiverPlayer){

        this.x = x;
        this.y = y;
        this.senderPlayer = senderPlayer;
        this.receiverPlayer =receiverPlayer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getSenderPlayer() {
        return senderPlayer;
    }

    public String getReceiverPlayer() {
        return receiverPlayer;
    }
}
