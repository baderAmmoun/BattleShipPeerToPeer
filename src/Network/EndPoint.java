package Network;

import Controller.MessageListener;
import Model.Coordinate;

public interface EndPoint {

    public String onCommand();

    public void onAction(String s);
}
