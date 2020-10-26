package View;

import Network.CreateServer;
import Network.EndPoint;
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


    public void CreateSocketServer(EndPoint endPoint) throws IOException {


        //endPoint.registerListener(label);

        CreateServer server = new CreateServer();
        server.registerEndPoint(endPoint);
        server.start();


    }

}
