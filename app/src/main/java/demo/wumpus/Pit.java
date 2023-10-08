package demo.wumpus;

public class Pit implements Perceptable{
  private final Room position;

  public Pit(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPercept(Player player) {
    Percept result = Percept.None;

    if(position.getNeighbours().contains(player.getPosition())) {
      result = Percept.Breeze;
    }

    return result;
  }
}
