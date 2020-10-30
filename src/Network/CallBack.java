package Network;

import Controller.CounterReceiver;

public class CallBack implements Runnable {

    private BattleShipProtocol protocol;
    private Request request;
    public CallBack(Request request ) {
        this.protocol=new CounterReceiver();

        protocol.injectRequest(request);
    }

    @Override
    public void run() {

            protocol.handleRequest();


    }
}
