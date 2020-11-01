package View;

import Network.Server;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindow {
    private Stage window;
    BattleViewFactory factory;

    public MainWindow(Stage window, BattleViewFactory factory) {
        this.window = window;
        this.factory = factory;
    }

    public void display() {
        factory.createLocalFleetView();
        factory.createAttackerView();
        factory.createPanelInfoView();
        window.setScene(factory.getScene());
        window.show();
//       label.setOnAction(e -> {
//            new CreateShot();
//
//        });
    }


    public void CreateSocketServer() throws IOException {


        //endPoint.registerListener(label);

        Server server = new Server();
        //server.registerEndPoint(endPoint);
        server.start();


    }

}
