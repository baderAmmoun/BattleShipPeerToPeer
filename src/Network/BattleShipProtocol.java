package Network;

public abstract class BattleShipProtocol {

    private Request request;
    private Respond respond;


    public void injectRequest(Request request){
        this.request=request;
    }

    public Request getRequest() {
        return request;
    }

    public Respond getRespond() {
        return respond;
    }

    public abstract void  handleRequest(Request request);
}
