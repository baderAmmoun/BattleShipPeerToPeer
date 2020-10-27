package Model;

import Controller.TowerControl;

import java.util.*;

public class Fleet {

    private static Fleet fleet;

    private List<Ship> aliveShips;
    private List<Ship> destroyedShips;
    private List<TowerControl> towerControlList;

    private Fleet() {
        aliveShips = new ArrayList<>();
        destroyedShips = new ArrayList<>();
        towerControlList =new ArrayList<>();
    }
    public static Fleet getFleet(){

        if (fleet==null)
            fleet=new Fleet();
        return fleet;
    }
    public void registerTower(TowerControl towerControl){
        this.towerControlList.add(towerControl);
    }

    public void addShip(Coordinate coordinate) {

        Ship ship = new Ship(coordinate);
        aliveShips.add(ship);

    }

    public void addShip(Ship ship) {
        aliveShips.add(ship);
        ship.setFleet(this);
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
            if (coordinate.equals(coordinate))
                map.put(true,ship);
        }
        map.put(true,null) ;
        return map;
    }

    public void beingAttacked(Ship ship) {

            aliveShips.remove(ship);
            destroyedShips.add(ship);
        }

        public void NotifyAll(String color,int x,int y){
        Iterator iterator=towerControlList.iterator();
        while (iterator.hasNext()){

            TowerControl towerControl=(TowerControl)iterator.next();
            towerControl.OnAction();
            towerControl.changeColor(color,x,y);
        }

        }

    }


