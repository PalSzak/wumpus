package demo.wumpus.events;

import demo.wumpus.WumpusWorld;

import java.util.List;

public interface GameAction {
  List<GameAction> run(WumpusWorld world);
}
