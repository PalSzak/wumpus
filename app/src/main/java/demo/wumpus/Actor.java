package demo.wumpus;

import demo.wumpus.events.GameAction;

import java.util.List;
import java.util.Optional;

public interface Actor {
  Optional<GameAction> takeAction(List<Percept> percepts);
  Room getPosition();

  boolean hadBump();
}
