package Model;

public class ClassicRoles implements Roles {

    private int numLocalShips;
    private int numRemoteShips;
    private int numCurrentLocalShips;
    private int numAttempts;
    private boolean isOpponentReady;
    private boolean isLocalReady;
    private int numRemoteAttempt;
    @Override
    public boolean isOpponentReady() {
      return isOpponentReady;
    }

    @Override
    public void setOpponent(boolean ready) {
        this.isOpponentReady=ready;
    }
    @Override
    public boolean isLocalReady() {
        return numLocalShips==numberOFShips() ;
    }

    @Override
    public boolean startGame() {
       return (isLocalReady() && isOpponentReady);
    }

    @Override
    public boolean endGame() {
        return (numAttempts== numberOfAttempts()&& numRemoteAttempt==numberOfAttempts());
    }

    @Override
    public int numberOFShips() {
     return 10;
    }

    @Override
    public int numberOfAttempts() {
        return 10;
    }

    @Override
    public boolean amIWine() {
        return numLocalShips>numRemoteShips;
    }

    @Override
    public void placeShip() {
        this.numLocalShips++;
    }

    @Override
    public void destroyShip() {
      this.numRemoteShips--;
    }

    @Override
    public void localAttempt() {
        this.numAttempts++;
    }
    @Override
    public void remoteAttempt() {
        this.numRemoteAttempt++;
    }
}
