package demo.wumpus;

import demo.wumpus.events.GameAction;

public class Game {
  private final Player player;
  private final WumpusWorld world;
  private Arrow arrow;

  public Game(Player player, WumpusWorld world) {
    this.player = player;
    this.world = world;
  }

  public Player getPlayer() {
    return player;
  }

  protected void setArrow(Arrow arrow) {
    this.arrow = arrow;
  }

  protected void nextRound(){
    GameAction gameAction = arrow.takeAction();
    gameAction.run(world);
  }
}
