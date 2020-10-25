package Model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fleet {
    private List<Ship> aliveShips;
    private List<Ship> destroyedShips;
    public Fleet(){
        aliveShips=new ArrayList<>();
        destroyedShips=new ArrayList<>();
    }

    public void addShip(int xCoordinate,int yCoordinate){

        Ship ship =new Ship(xCoordinate,yCoordinate);
        aliveShips.add(ship);

    }
    public void addShip(Ship ship){
        aliveShips.add(ship);
        ship.setFleet(this);
    }
    public int getNumberOfDestroyedShips(){
        return destroyedShips.size();
    }
    public List<Ship> getDestroyedShips(){
        return  destroyedShips;
    }

    public Ship isShipThere(Coordinate coordinate){
        Iterator iterator=aliveShips.iterator();
        while (iterator.hasNext()){
         Ship ship= (Ship)iterator.next();
         Coordinate shipCoordinate=ship.getCoordinate();
         if(coordinate.equals(coordinate))
             return ship;
        }
        return null;
    }
    public void beingAttacked(Coordinate coordinate){
        Ship ship=this.isShipThere(coordinate);
        if(ship!=null){
            aliveShips.remove(ship);
            destroyedShips.add(ship);
        }

    }

}
