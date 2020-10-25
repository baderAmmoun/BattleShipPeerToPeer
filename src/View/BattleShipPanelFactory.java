package View;

import javafx.scene.Scene;

public interface BattleShipPanelFactory{

    public void createFleetView();
    public void createAttackView();
    public Scene getScene();

}
