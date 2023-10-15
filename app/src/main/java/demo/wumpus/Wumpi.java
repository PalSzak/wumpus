package demo.wumpus;

public class Wumpi implements Perceptable {
  private final Room position;

  public Wumpi(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPerceptFrom(Room room) {
    return Percept.Stench;
  }
}
