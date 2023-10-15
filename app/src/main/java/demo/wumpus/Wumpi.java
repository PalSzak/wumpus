package demo.wumpus;

import java.util.Collection;

public class Wumpi implements Perceptable {
  private final Room position;

  public Wumpi(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPerceptFrom(Room room) {
    Collection<Room> stinkyPositions = position.getNeighbours();
    stinkyPositions.add(position);
    return stinkyPositions.contains(room) ? Percept.Stench : Percept.None;
  }
}
