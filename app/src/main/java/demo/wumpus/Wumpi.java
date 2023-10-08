package demo.wumpus;

public class Wumpi {
  private final Room position;

  public Wumpi(Room position) {
    this.position = position;
  }

  public Percept getPrecept() {
    return Percept.Stench;
  }
}
