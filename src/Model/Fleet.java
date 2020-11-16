package Model;

import java.util.*;

public class Fleet {

    private static Fleet fleet;
    private List<Ship> aliveShips;
    private List<Ship> destroyedShips;
    private List<TowerControl> enemiesTowers;
    private List<TowerControl> alliesTowers;


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
    public void addShip(Coordinate coordinate) {

        Ship ship = new Ship(coordinate);
        aliveShips.add(ship);
        ship.setFleet(this);
        this.notifyAlliesTowers("blue",coordinate.getXCoordinate(),coordinate.getYCoordinate());
    }

    public void addShip(Ship ship) {
        aliveShips.add(ship);
        ship.setFleet(this);
        this.notifyAlliesTowers("blue", ship.getCoordinate().getXCoordinate(),
                ship.getCoordinate().getYCoordinate());
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
        this.notifyAlliesTowers("white",ship.getCoordinate().getXCoordinate(),ship.getCoordinate().getYCoordinate());
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
            if (shipCoordinate.getXCoordinate() == coordinate.getXCoordinate() + 1 || shipCoordinate.getXCoordinate() == coordinate.getXCoordinate() - 1 || shipCoordinate.getXCoordinate() == coordinate.getXCoordinate())
                xNeighbor=true;
            if (shipCoordinate.getYCoordinate() == coordinate.getYCoordinate() + 1 || shipCoordinate.getYCoordinate() == coordinate.getYCoordinate() - 1|| shipCoordinate.getYCoordinate() == coordinate.getYCoordinate())
                yNeighbor=true;
            if(xNeighbor==true && yNeighbor==true)
                counter++;
        }
        return counter;

    }


}


