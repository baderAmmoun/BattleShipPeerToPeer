package View;

import Controller.PlaceShip;
import Model.Attack;
import Network.ConnectionManager;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;


public class FleeView implements BattleShipPanelFactory {

    private BorderPane layout;
    private int numRows;
    private int numCols;
    private PlaceShip placeShip;

    public FleeView(int numRows, int numCols) {
        this.layout = new BorderPane();
        this.numRows = numRows;
        this.numCols = numCols;
        this.placeShip = new PlaceShip();
    }

    @Override
    public void createFleetView() {


        GridPane gridFleet = new GridPane();
        gridFleet.setPadding((new Insets(10, 10, 10, 10)));
        gridFleet.setVgap(8);
        gridFleet.setHgap(10);
        this.placeShips(0, 0, false, gridFleet);

    }

    @Override
    public void createAttackView() {
        GridPane gridAttack = new GridPane();
        gridAttack.setPadding((new Insets(10, 10, 10, 10)));
        gridAttack.setVgap(8);
        gridAttack.setHgap(10);
        this.placeShips(0, 0, true, gridAttack);
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
        ship.setOnAction(event -> {
            BattleShipButton currentShip = (BattleShipButton) event.getSource();
            if (!currentShip.isShout()) {
                this.placeShip.registerTower(ship);
                this.placeShip.placeShipe(ship.getXcoordinate(), ship.getyCoordinate());
            }
            else{
                ConnectionManager.getConnectionManger().sendMessage(new Attack(ship.getXcoordinate(),ship.getyCoordinate(),"bader"),888);

            }

        });
        System.out.println("the number of rows is " + numRows + "and the number of cols is " + numCols);
        grid.add(ship, numRows, numCols);
        numCols++;
        this.placeShips(numRows, numCols, isShout, grid);

    }
}
