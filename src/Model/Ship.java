package Model;

public class Ship {
    private final Coordinate coordinate;
    private Fleet fleet;

    public Ship(Coordinate coordinate) {
        this.coordinate =coordinate;

    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public int[] shoot(Coordinate coordinate) {

        return new int[]{this.coordinate.getXCoordinate(), this.coordinate.getYCoordinate()};

    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }


}
