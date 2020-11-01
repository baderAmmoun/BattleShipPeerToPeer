package View;

import javafx.scene.Scene;

public interface BattleViewFactory {

    public void createLocalFleetView();

    public void createAttackerView();
    public void createPanelInfoView();

    public Scene getScene();

}
