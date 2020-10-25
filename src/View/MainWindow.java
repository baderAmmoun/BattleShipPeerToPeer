package View;

import Network.CreateServer;
import Network.CreateShot;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;


public class MainWindow {

    private Stage window;
    Button button;

    public MainWindow(Stage window){
        this.window=window;
        this.button=new Button("sent shot");

    }
    public void display() {

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene=new Scene(layout,200,200);
        window.setScene(scene);

        window.show();
        button.setOnAction(e -> {
            new CreateShot();

        });
    }


    public void CreateSocketServer() throws IOException {

        CreateServer server=new CreateServer();
        server.start();




    }
}
