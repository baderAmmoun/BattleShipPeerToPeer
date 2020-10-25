package Model;

public class Ship {
    private final Coordinate coordinate;
    private Fleet fleet;

    public Ship(int xCoordinate,int yCoordinate){
        this.coordinate=new Coordinate(xCoordinate,yCoordinate);

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int[] shoot(Coordinate coordinate){

        return new int[]{this.coordinate.getxCoordinate(),this.coordinate.getyCoordinate()};

    }
    public void setFleet(Fleet fleet){
        this.fleet=fleet;
    }



}
