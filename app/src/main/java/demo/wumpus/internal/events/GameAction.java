package demo.wumpus.internal.events;

import demo.wumpus.internal.WumpusWorld;

import java.util.List;

public interface GameAction {
  List<GameAction> run(WumpusWorld world);
}
