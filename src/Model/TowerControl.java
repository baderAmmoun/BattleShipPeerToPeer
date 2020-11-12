package Model;

public interface TowerControl {

    public void OnAction(int size);
    public void onCallback();
    public void changeColor(String color,int x,int y);
    public void startGame();
}
