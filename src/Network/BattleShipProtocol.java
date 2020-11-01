package Network;

public abstract class BattleShipProtocol {

    private Request request;
    private Respond respond;

    public void injectMessage(ExchangeableMessage message){
        if(message instanceof Request){
            this.request=(Request) message;
        }
        else {
            this.respond = (Respond) message;
        }

    }

    public Request getRequest() {
        return request;
    }

    public Respond getRespond() {
        return respond;
    }

    public void handleRequest() {
        if (this.request!=null) {
            this.respond = new Respond(-1, -1, request.getReceiverPlayer(), request.getSenderPlayer());
            handleStrikeRequest(request, respond);
            ConnectionManager.getConnectionManger().sendRespond(respond);
        } else {
            //this.respond = (Respond) request;
            System.out.println("I have recived respond for my request from " + respond.getSenderPlayer());
            handleResultOfStrike(respond);

        }
    }

    public abstract void handleStrikeRequest(Request request,Respond respond);
    public abstract void handleResultOfStrike(Respond respond);
    public boolean isStrike(Request request){

        return ! (request.getX()==-1 && request.getY()==-1) ;
    }
}
