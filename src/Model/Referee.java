package Model;

public class Referee {
    private static Referee referee;
    private Roles roles;
    private Audience audience;


    private Referee(){
    }
    public static Referee getInstance(){
        if(referee==null){
            referee=new Referee();
        }
        return referee;
    }
    public void registerRoles(Roles roles){
        this.roles=roles;
    }
    public void registerAudience(Audience audience){
        this.audience=audience;
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
    public void placeShip(){
        this.roles.placeShip();
    }
    public boolean startGame(){
        return roles.startGame();
    }
   public void  disLocalShip(){
        this.roles.disLocalShip();
   }
  public void notifyOnStartAudience(){
        this.audience.startGame();
  }
  public void retreatShip(){
        this.roles.retreatShip();
  }
}
