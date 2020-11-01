package Network;

public abstract class AbstractBattleShipProtocol {

    private Request request;
    private Respond respond;
    public void injectMessage(ExchangeableMessage message){
        if(message instanceof Request){
            this.request=(Request) message;
        }
        else {
            this.respond = (Respond) message; }
    }


    public Request getRequest() {
        return request;
    }

    public Respond getRespond() {
        return respond;
    }

    public void handleRequest() {
        if (this.request != null) {
            this.respond = new Respond(this.request.getX(), this.request.getY(), request.getReceiverPlayer(), request.getSenderPlayer());
            handleStrikeRequest(request, respond);
            ConnectionManager.getConnectionManger().sendRespond(respond);
        } else {
            handleResultOfStrike(respond);

        }
    }

    public abstract void handleStrikeRequest(Request request,Respond respond);
    public abstract void handleResultOfStrike(Respond respond);

}
