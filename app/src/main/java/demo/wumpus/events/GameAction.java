package demo.wumpus.events;

import demo.wumpus.WumpusWorld;

public interface GameAction {
  public void run(WumpusWorld world);
}
