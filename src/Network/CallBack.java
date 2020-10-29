package Network;

import Controller.CounterReciever;

public class CallBack implements Runnable {

    private BattleShipProtocol protocol;
    public CallBack(Request request ) {
        this.protocol=new CounterReciever();
        protocol.injectRequest(request);
    }

    @Override
    public void run() {

            protocol.handleRequest(protocol.getRequest());


    }
}
