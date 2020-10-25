package View;

import Controller.MessageListener;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class BattleShipLabel extends Label implements MessageListener {
    @Override
    public void getMessage(String message) {
        System.out.println("I will print here from ui");
        Platform.runLater(() -> this.setText(message));
        //button.setText(message);
    }
}
