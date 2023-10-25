package demo.wumpus;

import demo.wumpus.events.GameAction;

import java.util.Objects;

public class Game {
  private Player player;
  private WumpusWorld world;
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

  protected void setPlayer(Player player) {
    this.player = player;
  }

  protected void setWorld(WumpusWorld world) {
    this.world = world;
  }

  protected void nextRound(){
    if(Objects.nonNull(arrow)) {
      GameAction gameAction = arrow.takeAction();
      gameAction.run(world);
    }

    player.takeAction(world.getPerceptsOf(player));
  }
}
