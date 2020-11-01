package Network;

import Controller.CounterReceiver;

public class CallBack implements Runnable {

    private BattleShipProtocol protocol;
    private ExchangeableMessage request;
    public CallBack(ExchangeableMessage request ) {
        this.protocol=new CounterReceiver();

        protocol.injectMessage(request);
    }

    @Override
    public void run() {

            protocol.handleRequest();


    }
}
