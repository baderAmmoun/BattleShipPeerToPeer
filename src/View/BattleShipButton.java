package View;

import Controller.MessageListener;
import javafx.application.Platform;
import javafx.scene.control.Button;


public class BattleShipButton extends Button implements MessageListener {
    private final int xcoordinate;
    private final int yCoordinate;
    private final boolean isShout;

    public BattleShipButton(int xcoordinate, int yCoordinate,boolean isShout) {
        super();
        this.xcoordinate = xcoordinate;
        this.yCoordinate = yCoordinate;
        this.isShout=isShout;
    }

    @Override
    public void getMessage(String message) {
        System.out.println("I will print here from ui");
        Platform.runLater(() -> this.setStyle("-fx-background-color: black"));

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
}
