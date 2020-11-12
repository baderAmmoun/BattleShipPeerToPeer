package Model;

import Controller.TowerControl;

import java.util.*;

public class Fleet {

    private static Fleet fleet;
    private List<Ship> aliveShips;
    private List<Ship> destroyedShips;
    private List<TowerControl> enemiesTowers;
    private List<TowerControl> alliesTowers;
    private Roles roles;

    private Fleet() {
        aliveShips = new ArrayList<>();
        destroyedShips = new ArrayList<>();
        enemiesTowers =new ArrayList<>();
        alliesTowers=new ArrayList<>();
    }
    public static Fleet getFleet(){

        if (fleet==null)
            fleet=new Fleet();
        return fleet;
    }
    public void registerEnemiesTower(TowerControl towerControl){
        this.enemiesTowers.add(towerControl);
    }
    public void registerAlliesTowers(TowerControl towerControl){
        this.alliesTowers.add(towerControl);
    }
    public void registerRoles(Roles roles){
        this.roles=roles;
    }
    public void addShip(Coordinate coordinate) {

        Ship ship = new Ship(coordinate);
        aliveShips.add(ship);
        ship.setFleet(this);
        this.notifyAlliesTowers("blue",coordinate.getxCoordinate(),coordinate.getyCoordinate());
    }

    public void addShip(Ship ship) {
        aliveShips.add(ship);
        ship.setFleet(this);
        this.notifyAlliesTowers("blue", ship.getCoordinate().getxCoordinate(),
                ship.getCoordinate().getyCoordinate());
        this.roles.placeShip();
        if (roles.startGame()) {
            Iterator<TowerControl> iterator = this.enemiesTowers.iterator();
        while(iterator.hasNext()){
            iterator.next().startGame();
        }
        }
    }

    public int getNumberOfDestroyedShips() {
        return destroyedShips.size();
    }

    public List<Ship> getDestroyedShips() {
        return destroyedShips;
    }

    public Map<Boolean,Ship> isShipThere(Coordinate coordinate) {

        HashMap map=new HashMap<>();
        Iterator iterator = aliveShips.iterator();
        while (iterator.hasNext()) {
            Ship ship = (Ship) iterator.next();
            Coordinate shipCoordinate = ship.getCoordinate();
            if (coordinate.equals(shipCoordinate)) {
                map.put(true, ship);
                System.out.println("I find ship here");
                roles.disLocalShip();
            }
        }
        map.put(false, null);
        return map;
    }
   public void removeShip(Ship ship) {
    boolean isRemoved=   aliveShips.removeIf(currentShip -> {
           return currentShip.getCoordinate().equals(ship.getCoordinate());
       });

       if(isRemoved){
        this.notifyAlliesTowers("white",ship.getCoordinate().getxCoordinate(),ship.getCoordinate().getyCoordinate());
    }
   }
    public void beingAttacked(Ship ship) {

        aliveShips.remove(ship);
        destroyedShips.add(ship);

    }

    public void notifyEnemiesTowers(String color, int x, int y) {
        Iterator iterator = enemiesTowers.iterator();
        while (iterator.hasNext()) {

            TowerControl towerControl = (TowerControl) iterator.next();
            towerControl.onCallback();
            towerControl.changeColor(color, x, y);
        }

    }

    public void notifyAlliesTowers(String color, int x, int y) {
        Iterator iterator = alliesTowers.iterator();
        while (iterator.hasNext()) {

            TowerControl towerControl = (TowerControl) iterator.next();
            towerControl.OnAction(this.aliveShips.size());
            towerControl.changeColor(color, x, y);
        }

    }

    public int countOfNeighborShip(Coordinate coordinate) {
        int counter = 0;

        Iterator iterator = aliveShips.iterator();
        while (iterator.hasNext()) {
            boolean xNeighbor=false;
            boolean yNeighbor=false;
            Ship ship = (Ship) iterator.next();
            Coordinate shipCoordinate = ship.getCoordinate();
            if (shipCoordinate.getxCoordinate() == coordinate.getxCoordinate() + 1 || shipCoordinate.getxCoordinate() == coordinate.getxCoordinate() - 1 || shipCoordinate.getxCoordinate() == coordinate.getxCoordinate())
                xNeighbor=true;
            if (shipCoordinate.getyCoordinate() == coordinate.getyCoordinate() + 1 || shipCoordinate.getyCoordinate() == coordinate.getyCoordinate() - 1|| shipCoordinate.getyCoordinate() == coordinate.getyCoordinate())
                yNeighbor=true;
            if(xNeighbor==true && yNeighbor==true)
                counter++;
        }
        return counter;

    }

    public void riseRemoteReadiness(){
        roles.riseRemoteReadiness();
    }

    public boolean isGameStart(){
        return this.roles.startGame();
    }
    public boolean isLocalReady(){
        return this.roles.isLocalReady();
    }
    public void destroyRemotedShips(){
        this.roles.destroyRemoteShip();
    }
    public void numRemoteAttack(){
        this.roles.numRemoteAttack();
    }
    public  boolean isGameEnd(){
        return this.roles.endGame();
    }
    public boolean amIWin(){

        return this.roles.amIWine();
    }
    public void increaseLocalAttempt(){
        this.roles.numLocalAttack();
    }
   public int limitOfCounters(){
        return this.roles.numberOfLimitAttack();
   }
}


