package View;

import Controller.CounterReceiver;
import Controller.PlaceShip;
import Controller.TowerControl;
import Network.ConnectionManager;
import Network.Request;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.util.Iterator;


public class BattleViewClassic implements BattleViewFactory, TowerControl {

   private static BattleViewClassic battleViewClassic;
   private BorderPane layout;
    private int numRows;
    private int numCols;
    private PlaceShip placeShip;
    private Label localPlayer;
    private Label remotePlayer;
    private Label destroyedOpponentShips;
    private Label intactShips;
    private  BattleViewClassic(int numRows, int numCols) {
        this.layout = new BorderPane();
        this.numRows = numRows;
        this.numCols = numCols;
        this.placeShip = new PlaceShip();
        CounterReceiver.registerTowerControl(this);
    }
    public static BattleViewClassic getInstance(){
        if (battleViewClassic==null) {
            battleViewClassic = new BattleViewClassic(10, 12);
           return battleViewClassic;
        }
            else
            return battleViewClassic;

    }

    @Override
    public void createLocalFleetView() {

        GridPane gridLocalFleet = new GridPane();
        gridLocalFleet.setMaxHeight(50);
        gridLocalFleet.setStyle("--fx-background-color: black;" +
                        "-fx-background-insets: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
        gridLocalFleet.setPadding((new Insets(10, 10, 10, 10)));
        gridLocalFleet.setVgap(2);
        gridLocalFleet.setHgap(3);
        this.placeShips(0, 0, false, gridLocalFleet);

    }

    @Override
    public void createAttackerView() {
        GridPane gridAttackerPanel = new GridPane();
        gridAttackerPanel.setMaxHeight(50);
        gridAttackerPanel.setStyle("--fx-background-color: black;" +
                "-fx-background-insets: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(three-pass-box, black, 10, 0, 0, 0);");
        gridAttackerPanel.setPadding((new Insets(10, 10, 10, 10)));
        gridAttackerPanel.setVgap(2);
        gridAttackerPanel.setHgap(3);
        this.placeShips(0, 0, true, gridAttackerPanel);
    }

    @Override
    public void createPanelInfoView() {
        VBox vBox1=new VBox(10);
        vBox1.setPrefWidth(200);
        this.localPlayer=new Label();
        this.intactShips=new Label("Number Of Intact Ships: \n "+"       12");
        this.localPlayer.setText("Your name :\n "+" Bader");
       this.decorateLabel(intactShips);
       this.decorateLabel(localPlayer);
        vBox1.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox1.getChildren().addAll(this.localPlayer,this.intactShips);

        VBox vBox2=new VBox(10);
        vBox2.setPrefWidth(200);
        this.remotePlayer=new Label();
        this.destroyedOpponentShips=new Label("Number Of Destroyed Ships: \n "+"       12");
        this.remotePlayer.setText("You play with  :\n "+" feras");
        this.decorateLabel(remotePlayer);
        this.decorateLabel(destroyedOpponentShips);
        vBox2.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox2.getChildren().addAll(this.remotePlayer,this.destroyedOpponentShips);

        HBox hBox=new HBox(90);
        hBox.getChildren().addAll(vBox1,vBox2);
        this.layout.setTop(hBox);



    }
    private void decorateLabel(Label label){
        label.setTextFill(Color.WHITE);
        label.setFont(new Font(12));
    }

    @Override
    public Scene getScene() {
        return new Scene(this.layout, 500, 400);

    }

    private void placeShips(int numRows, int numCols, boolean isShout, GridPane grid) {

        if (this.numRows == numRows && this.numCols == numCols) {
            if (isShout)
                this.layout.setRight(grid);
            else
                this.layout.setLeft(grid);
            return;
        }
        if (numCols == this.numCols) {
            numCols = 0;
            numRows++;
            System.out.println("the number of rows is " + numRows + "and the number of cols is " + numCols);
        }

        BattleShipButton ship = new BattleShipButton(numRows, numCols, isShout);
        ship.setMaxSize(4,4);
        ship.setOnAction(event -> {
            BattleShipButton currentShip = (BattleShipButton) event.getSource();
            if (!currentShip.isShout()) {
                this.placeShip.registerTower(ship);
                this.placeShip.placeShipe(ship.getXcoordinate(), ship.getyCoordinate());
            }
            else{
                ConnectionManager.getConnectionManger().sendMessage(new Request(ship.getXcoordinate(),ship.getyCoordinate(),"feras","bader"),888);
            }
        });
        System.out.println("the number of rows is " + numRows + "and the number of cols is " + numCols);
        grid.add(ship, numRows, numCols);
        numCols++;
        this.placeShips(numRows, numCols, isShout, grid);
    }

    @Override
    public void OnAction() {

    }

    @Override
    public void changeColor(String color,int x, int y) {
        GridPane grade=(GridPane) this.layout.getLeft();
        FilteredList<Node> currentNode=grade.getChildren().filtered((node)->{
          if(GridPane.getRowIndex(node)==x&& GridPane.getColumnIndex(node)==y);
          return true;
        });
        Iterator iterator =currentNode.iterator();
        while (iterator.hasNext()){

            BattleShipButton battleShipButton=(BattleShipButton) iterator.next();
            if(battleShipButton.getXcoordinate()==x && battleShipButton.getyCoordinate()==y){

                battleShipButton.setStyle("-fx-background-color:" + color);
            }
        }

    }
    public void NumNeighborShips(int num,int x, int y) {
        GridPane grade=(GridPane) this.layout.getRight();
        FilteredList<Node> currentNode=grade.getChildren().filtered((node)->{
            if(GridPane.getRowIndex(node)==x&& GridPane.getColumnIndex(node)==y);
            return true;
        });
        Iterator iterator =currentNode.iterator();
        while (iterator.hasNext()){

            BattleShipButton battleShipButton=(BattleShipButton) iterator.next();
            if(battleShipButton.getXcoordinate()==x && battleShipButton.getyCoordinate()==y){

               Platform.runLater(() -> {
                           battleShipButton.setText(Integer.toString(num));
                   battleShipButton.setMaxSize(1,1);


                       }
               );
            }
        }

    }

    @Override
    public void attack() {

    }
}
