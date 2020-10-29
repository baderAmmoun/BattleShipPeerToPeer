package Network;

import Controller.CounterReceiver;

public class CallBack implements Runnable {

    private BattleShipProtocol protocol;
    public CallBack(Request request ) {
        this.protocol=new CounterReceiver();
        protocol.injectRequest(request);
    }

    @Override
    public void run() {

            protocol.handleRequest(protocol.getRequest());


    }
}
