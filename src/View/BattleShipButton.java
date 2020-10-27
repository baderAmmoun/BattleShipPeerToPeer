package View;

import Controller.TowerControl;
import javafx.application.Platform;
import javafx.scene.control.Button;


public class BattleShipButton extends Button implements TowerControl {
    private final int xcoordinate;
    private final int yCoordinate;
    private final boolean isShout;

    public BattleShipButton(int xcoordinate, int yCoordinate, boolean isShout) {
        super();
        this.xcoordinate = xcoordinate;
        this.yCoordinate = yCoordinate;
        this.isShout = isShout;
    }

    public int getXcoordinate() {
        return xcoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public boolean isShout() {
        return isShout;
    }

    @Override
    public void OnAction() {
        System.out.println("I will print here from ui");
    }

    @Override
    public void changeColor() {
        Platform.runLater(() -> this.setStyle("-fx-background-color: blue"));

    }

    @Override
    public void attack() {

    }
}
