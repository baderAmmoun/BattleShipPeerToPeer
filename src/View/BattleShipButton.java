package View;


import Controller.ActionObserver;
import javafx.application.Platform;
import javafx.scene.control.Button;


public class BattleShipButton extends Button implements ActionObserver {
    private final int xCoordinate;
    private final int yCoordinate;
    private final boolean isShout;

    public BattleShipButton(int xcoordinate, int yCoordinate, boolean isShout) {
        super();
        this.xCoordinate = xcoordinate;
        this.yCoordinate = yCoordinate;
        this.isShout = isShout;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public boolean isShout() {
        return isShout;
    }

    @Override
    public void OnAction(int size) {
        System.out.println("I will print here from ui");
    }

    @Override
    public void onCallback() {

    }

    @Override
    public void changeColor(String color,int x,int y) {

        if (this.xCoordinate == x && this.yCoordinate == y) {


            Platform.runLater(() -> {
                        this.setStyle("-fx-background-color:" + color);

                    }
            );
        }

    }

    @Override
    public void startGame() {

    }
}
