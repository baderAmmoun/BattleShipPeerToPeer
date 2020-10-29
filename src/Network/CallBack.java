package Network;

public class CallBack implements Runnable {

    private BattleShipProtocol protocol;
    public CallBack(BattleShipProtocol protocol,Request request ) {
        this.protocol=protocol;
        protocol.injectRequest(request);
    }

    @Override
    public void run() {
        protocol.handleRequest(protocol.getRequest());

    }
}
