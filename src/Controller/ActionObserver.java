package Controller;

public interface ActionObserver {
    public void OnAction(int size);
    public void onCallback();
    public void changeColor(String color,int x,int y);
    public void startGame();
}
