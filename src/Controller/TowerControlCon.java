package Controller;

import Model.TowerControl;

public class TowerControlCon implements TowerControl {

    private ActionObserver actionObserver;
    public TowerControlCon(ActionObserver actionObserver){
        this.actionObserver=actionObserver;
    }
    @Override
    public void OnAction(int size) {
        this.actionObserver.OnAction(size);
    }

    @Override
    public void onCallback() {
     this.actionObserver.onCallback();
    }

    @Override
    public void changeColor(String color, int x, int y) {
      this.actionObserver.changeColor(color,x,y);
    }

    @Override
    public void startGame() {
    this.actionObserver.startGame();
    }
}
