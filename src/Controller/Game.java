package Controller;

import Model.Coordinate;
import Network.EndPoint;

public class Game implements EndPoint {

    MessageListener listener;
    @Override
    public void onCommand(String s) {
        System.out.println("here is the on commnd method");
      listener.getMessage(s);

    }

    @Override
    public void registerListener(MessageListener messageListener) {
        this.listener=messageListener;
    }
}
