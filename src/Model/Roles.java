package Model;

public interface Roles {



    public void riseRemoteReadiness();
    public boolean isLocalReady();
    public boolean startGame();
    public boolean endGame();
    public int numberOFLimitPlaceShips();
    public int numberOfLimitAttack();
    public boolean amIWine();
    public void placeShip();
    public void retreatShip();
    public void destroyRemoteShip();
    public void disLocalShip();
    public void numLocalAttack();
    public void numRemoteAttack();
}
