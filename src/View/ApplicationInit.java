package View;

import Network.Server;
import javafx.stage.Stage;

import java.io.IOException;


public class ApplicationInit {
    private Stage window;
    BattleViewFactory factory;

    public ApplicationInit(Stage window, BattleViewFactory factory) throws Exception {
        this.window = window;
        this.factory = factory;
        this.CreateSocketServer();
        this.display();

    }

    private void display() throws Exception {
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


    private void CreateSocketServer() throws IOException {

        Server server = new Server();
        server.setDaemon(true);
        server.start();


    }

}
