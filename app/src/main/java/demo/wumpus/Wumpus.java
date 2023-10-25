package demo.wumpus;

import demo.wumpus.events.GameAction;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Wumpus implements Perceptable, Figure {
  private final Room position;
  private boolean scream;
  private boolean alive;

  public Wumpus(Room position) {
    this.position = position;
    scream = false;
    alive = true;
  }

  @Override
  public Percept getPerceptFrom(Room room) {
    Collection<Room> stinkyPositions = position.getNeighbours();
    stinkyPositions.add(position);
    return stinkyPositions.contains(room) ? Percept.Stench : Percept.None;
  }

  public void die() {
    scream = true;
    alive = false;
  }

  public boolean isAlive() {
    return alive;
  }

  public Percept hadScream() {
    return scream ? Percept.Scream : Percept.None;
  }

  public void nextRound() {
    scream = false;
  }

  @Override
  public List<GameAction> takeAction(List<Percept> percepts) {
    return Collections.emptyList();
  }

  @Override
  public Room getPosition() {
    return position;
  }
}
