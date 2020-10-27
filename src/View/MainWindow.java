package View;

import Network.Server;
import javafx.stage.Stage;

import java.io.IOException;


public class MainWindow {
    private Stage window;
    BattleShipPanelFactory factory;

    public MainWindow(Stage window, BattleShipPanelFactory factory) {
        this.window = window;
        this.factory = factory;
    }

    public void display() {
        factory.createFleetView();
        factory.createAttackView();
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
