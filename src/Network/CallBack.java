package Network;

import Controller.CounterReceiver;

public class CallBack implements Runnable {

    private BattleShipProtocol protocol;
    ExchangeableMessage message;
    public CallBack(ExchangeableMessage message ) {
        this.protocol=new CounterReceiver();
        this.message=message;

    }

    @Override
    public void run() {

            protocol.handleRequest(message);


    }
}
