package Network;

import java.io.Serializable;

public class Request extends ExchangeableMessage{

    public Request(int x, int y, String senderPlayer,String receiverPlayer) {
        super(x,y,senderPlayer,receiverPlayer);

    }


}

