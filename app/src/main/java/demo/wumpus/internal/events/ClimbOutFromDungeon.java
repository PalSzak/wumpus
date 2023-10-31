package demo.wumpus.internal.events;

import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.figures.Player;

import java.util.List;

public class ClimbOutFromDungeon implements GameAction {
  private final Player player;

  public ClimbOutFromDungeon(Player player) {
    this.player = player;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    return List.of(new RemoveFigure(player));
  }
}
