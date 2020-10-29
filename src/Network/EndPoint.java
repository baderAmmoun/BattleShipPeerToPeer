package Network;


import Model.Attack;


public interface EndPoint {

    public String onCommand(Attack attack);

    public void onAction(String s);
}
