package Controller;

import Configuration.Config;
import Model.*;
import Network.ConnectionManager;
import Network.Request;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PlaceShip implements TowerControl,Audience {

    private Fleet fleet;
    private Referee referee;
    private List<ActionObserver> actionObserver;
    private RolesObserver rolesObserver;

    public PlaceShip(){
        this.fleet=Fleet.getFleet();
        this.referee=Referee.getInstance();
        this.referee.registerRoles(new ClassicRoles());
        fleet.registerAlliesTowers(this);
        referee.registerAudience(this);
         this.actionObserver=new ArrayList<>();

    }

    public void registerActionObserver(ActionObserver actionObserver){
         this.actionObserver.add(actionObserver);


    }
    public void registerRolesObserver(RolesObserver rolesObserver){

        this.rolesObserver=rolesObserver;

    }

    public void placeShip(int xCoordinate, int yCoordinate){
        if(referee.isLocalReady())
            return;
        Coordinate coordinate=new Coordinate(xCoordinate,yCoordinate);

        Map<Boolean,Ship> map=fleet.isShipThere(coordinate);
        if(map.containsKey(true)){
            Ship ship =map.get(true);
            fleet.removeShip(ship);
            referee.retreatShip();
            return;
        }
        Ship ship=new Ship(coordinate);

         this.fleet.addShip(ship);
         this.referee.placeShip();
         if(this.referee.startGame()){
             this.referee.notifyOnStartAudience();
         }
        if (this.referee.isLocalReady()){
            Config config= null;
            try {
                config = Config.getConfig();
            } catch (Exception e) {
                e.printStackTrace();
            }
            int remotePort=Integer.parseInt(config.getValue("remotePort"));
            String sender=config.getValue("sender");
            String receiver=config.getValue("receiver");
            Request request=new Request(-1,-1,sender,receiver);
            ConnectionManager.getConnectionManger().sendMessage(request,remotePort);

        }


    }

    @Override
    public void endGame() {
        this.rolesObserver.endGame();
    }

    @Override
    public void OnAction(int size) {
        Iterator iterator = actionObserver.iterator();
        while (iterator.hasNext()) {

            ActionObserver actionObserver = (ActionObserver) iterator.next();
            actionObserver.OnAction(size);

        }
    }

    @Override
    public void onCallback() {
        System.out.println("here in call back");
        Iterator iterator = actionObserver.iterator();
        while (iterator.hasNext()) {

            ActionObserver actionObserver = (ActionObserver) iterator.next();
            actionObserver.onCallback();

        }
    }

    @Override
    public void changeColor(String color, int x, int y) {
        Iterator iterator = actionObserver.iterator();
        while (iterator.hasNext()) {

            ActionObserver actionObserver = (ActionObserver) iterator.next();
            actionObserver.changeColor(color,x,y);

        }
    }

    @Override
    public void startGame() {
     this.rolesObserver.startGame();
    }
}
