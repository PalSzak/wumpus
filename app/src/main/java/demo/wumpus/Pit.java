package demo.wumpus;

public class Pit implements Perceptable{
  private final Room position;

  public Pit(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPercept(Player player) {
    return Percept.Breeze;
  }
}
