package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.events.GameAction;

import java.util.List;

public interface Figure {
  List<GameAction> takeAction(WumpusWorld world);
  Room getPosition();
}
