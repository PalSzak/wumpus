package demo.wumpus.internal.events;

import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.figures.Player;

import java.util.Collections;
import java.util.List;

public class TurnLeft implements GameAction{
  private final Player player;

  public TurnLeft(Player player) {
    this.player = player;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    player.turnLeft();
    return Collections.emptyList();
  }
}
