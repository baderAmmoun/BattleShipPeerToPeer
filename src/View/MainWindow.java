package View;

import Network.CreateServer;
import Network.CreateShot;
import Network.EndPoint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainWindow  {

    private Stage window;
    //Button button;
    BattleShipButton label;


    public MainWindow(Stage window){
        this.window=window;
       // this.button=new Button("sent shot");
        this.label=new BattleShipButton();

    }
    public void display() {

        StackPane layout = new StackPane();
        layout.getChildren().add(label);
        //layout.getChildren().add(button);
        Scene scene=new Scene(layout,200,200);
        window.setScene(scene);

        window.show();
       label.setOnAction(e -> {
            new CreateShot();

        });
    }


    public void CreateSocketServer(EndPoint endPoint) throws IOException {


        endPoint.registerListener(label);

        CreateServer server=new CreateServer();
        server.registerEndPoint(endPoint);
        server.start();




    }

}
