package demo.wumpus;

public class Gold implements Perceptable {

  private final Room position;

  public Gold(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPercept(Player player) {
    Percept result = Percept.None;
    if(position.equals(player.getPosition())){
      result = Percept.Glitter;
    }
    return result;
  }
}
