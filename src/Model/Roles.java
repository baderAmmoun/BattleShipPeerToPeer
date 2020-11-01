package Model;

public interface Roles {



    public void setOpponent(boolean ready);
    public boolean isOpponentReady();
    public boolean isLocalReady();
    public boolean startGame();
    public boolean endGame();
    public int numberOFShips();
    public int  numberOfAttempts();
    public boolean amIWine();
    public void placeShip();
    public void destroyShip();
    public void localAttempt();
    public void remoteAttempt();
}
