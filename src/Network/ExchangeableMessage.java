package Network;


import java.io.Serializable;

public class ExchangeableMessage implements Serializable {
    private static final long serialVersionUID = 4L;
    private final int x;
    private final int y;
    private final String senderPlayer;
    private final String receiverPlayer;

    public ExchangeableMessage(int x, int y, String SenderPlayer, String ReceiverPlayer) {
        this.x = x;
        this.y = y;
        this.senderPlayer = SenderPlayer;
        this.receiverPlayer = ReceiverPlayer;
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

