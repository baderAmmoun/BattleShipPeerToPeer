package View;

import Controller.MessageListener;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class BattleShipButton extends Button implements MessageListener {
    @Override
    public void getMessage(String message) {
        System.out.println("I will print here from ui");
        Platform.runLater(() -> this.setStyle("-fx-background-color: black"));
        //button.setText(message);
    }
}
