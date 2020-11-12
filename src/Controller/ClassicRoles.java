package Controller;

import Model.Roles;

public class ClassicRoles implements Roles {

    private int numLocalShips;
    private int numRemoteDisShips;
    private int numLocalAttacks;
    private boolean isOpponentReady;
    private boolean isLocalReady;
    private int numRemoteAttacks;


    @Override
    public void riseRemoteReadiness() {

        this.isOpponentReady = true;
    }

    @Override
    public boolean isLocalReady() {

        return numLocalShips == numberOFLimitPlaceShips();
    }

    @Override
    public boolean startGame() {

        return (isLocalReady() && isOpponentReady);
    }

    @Override
    public boolean endGame() {
        return (numLocalAttacks == numberOfLimitAttack()&& numRemoteAttacks == numberOfLimitAttack());
    }

    @Override
    public int numberOFLimitPlaceShips() {

        return 10;
    }

    @Override
    public int numberOfLimitAttack() {

        return 10;
    }

    @Override
    public boolean amIWine() {

        return numLocalShips> this.numberOFLimitPlaceShips()-numRemoteDisShips;
    }

    @Override
    public void placeShip() {

        this.numLocalShips++;
    }

    @Override
    public void destroyRemoteShip() {

        this.numRemoteDisShips++;
    }

    @Override
    public void disLocalShip() {

        this.numLocalShips--;
    }

    @Override
    public void numLocalAttack() {
        this.numLocalAttacks++;
    }
    @Override
    public void numRemoteAttack() {
        this.numRemoteAttacks++;
    }
}
