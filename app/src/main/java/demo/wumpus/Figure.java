package demo.wumpus;

import demo.wumpus.events.GameAction;

import java.util.List;

public interface Figure {
  List<GameAction> takeAction(List<Percept> percepts);
  Room getPosition();
}
