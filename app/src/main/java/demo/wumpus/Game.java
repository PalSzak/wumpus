package demo.wumpus;

public class Game {
  private final Player player;
  private Arrow arrow;

  public Game(Player player) {
    this.player = player;
  }

  public Player getPlayer() {
    return player;
  }

  protected void setArrow(Arrow arrow) {
    this.arrow = arrow;
  }

  protected void nextRound(){
    arrow.takeAction();
  }
}
