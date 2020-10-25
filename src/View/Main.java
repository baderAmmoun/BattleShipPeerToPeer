package View;

import Controller.Game;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainWindow mainWindow=new MainWindow(primaryStage,new FleeView(3,3));
        mainWindow.display();
        mainWindow.CreateSocketServer(new Game());
    }
}
