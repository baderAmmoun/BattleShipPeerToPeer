import View.ApplicationInit;
import View.BattleViewClassic;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationInit mainWindow=new ApplicationInit(primaryStage, BattleViewClassic.getInstance());

    }
}
