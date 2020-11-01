package Network;

import Controller.BattleShipProtocol;

public class CallBack implements Runnable {

    private AbstractBattleShipProtocol protocol;

    private ExchangeableMessage request;
    public CallBack(ExchangeableMessage request ) {
        this.protocol=new BattleShipProtocol();

        protocol.injectMessage(request);
    }

    @Override
    public void run() {
        protocol.handleRequest();
    }
}
