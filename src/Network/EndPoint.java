package Network;

import Controller.MessageListener;
import Model.Coordinate;

public interface EndPoint {

    public void onCommand(String s);

    public void registerListener(MessageListener messageListener);
}
