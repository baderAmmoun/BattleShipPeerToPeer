package Network;

import Model.Attack;

public class CallBack implements Runnable {

    private Attack attack;
    private EndPoint endPoint;
    public CallBack(Attack attack,EndPoint endPoint) {
        this.endPoint=endPoint;
        this.attack = attack;
    }

    @Override
    public void run() {
        endPoint.onCommand(attack);

    }
}
