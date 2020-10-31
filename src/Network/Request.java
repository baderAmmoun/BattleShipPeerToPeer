package Network;

import java.io.Serializable;

public class Request implements Serializable {
    private static final long serialVersionUID = 4L;
    private final int x;
    private final int y;
    private final String senderPlayer;
    private final String receiverPlayer;
    private final String endpoint;
    public Request(int x, int y, String SenderPlayer,String ReceiverPlayer,String endpoint) {
        this.x = x;
        this.y = y;
        this.senderPlayer = SenderPlayer;
        this.receiverPlayer =ReceiverPlayer;
        this.endpoint=endpoint;
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

    public String getEndpoint() {
        return endpoint;
    }
}

