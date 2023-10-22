package demo.wumpus;

import java.util.Collection;

public class Wumpus implements Perceptable {
  private final Room position;
  private boolean scream;

  public Wumpus(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPerceptFrom(Room room) {
    Collection<Room> stinkyPositions = position.getNeighbours();
    stinkyPositions.add(position);
    return stinkyPositions.contains(room) ? Percept.Stench : Percept.None;
  }

  public void die() {
    scream = true;
  }

  public boolean hadScream() {
    return scream;
  }

  public void nextRound() {
    scream = false;
  }
}
