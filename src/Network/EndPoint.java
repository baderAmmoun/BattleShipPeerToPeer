package Network;

import Controller.MessageListener;
import Model.Attack;
import Model.Coordinate;

public interface EndPoint {

    public String onCommand(Attack attack);

    public void onAction(String s);
}
