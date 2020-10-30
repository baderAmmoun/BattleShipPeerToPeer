package Network;

public abstract class BattleShipProtocol {

    private Request request;
    private Respond respond;

    public Request getRequest() {
        return request;
    }

    public Respond getRespond() {
        return respond;
    }

    public void handleRequest(ExchangeableMessage message){
        if (this.isStrike(message)) {
            this.request= (Request) message;
            respond=new Respond(-1,-1,request.getReceiverPlayer(),request.getSenderPlayer());
            handleStrikeRequest(request, respond);
            ConnectionManager.getConnectionManger().sendRespond(respond);
        }
        else
             this.respond=(Respond) message;
            handleResultOfStrike(respond);

    }

    public abstract void handleStrikeRequest(Request request,Respond respond);
    public abstract void handleResultOfStrike(Respond respond);
    public boolean isStrike(ExchangeableMessage message){

        return ! (message.getX()==-1 && message.getY()==-1) ;
    }
}
