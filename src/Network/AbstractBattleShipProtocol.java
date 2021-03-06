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
            if(this.request.getX()==-1 && this.request.getY()==-1){
                this.startGame(request);
                System.out.println("the start game is begin");
            }
            else {
                this.respond = new Respond(this.request.getX(), this.request.getY(), request.getReceiverPlayer(), request.getSenderPlayer());
                handleStrikeRequest(request, respond);
                ConnectionManager.getConnectionManger().sendRespond(respond);
            }
        } else {
            handleResultOfStrike(respond);
            if(respond.isGameEnd())
                endGame(respond);

            System.out.println("I have recived the respond");

        }
    }

    public abstract void handleStrikeRequest(Request request,Respond respond);
    public abstract void handleResultOfStrike(Respond respond);
    public abstract void startGame(Request request);
    public abstract void  endGame(Respond respond);

}
