package demo.wumpus;

public class Pit implements Perceptable{
  private final Room position;

  public Pit(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPerceptFrom(Room room) {
    Percept result = Percept.None;

    if(position.getNeighbours().contains(room)) {
      result = Percept.Breeze;
    }

    return result;
  }
}
