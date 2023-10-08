package demo.wumpus;

public class Gold implements Perceptable{

  private final Coord position;

  public Gold(Coord position) {
    this.position = position;
  }

  public Percept getPercept(Player player) {
    Percept result = Percept.None;
    if(position.equals(player.getPosition())){
      result = Percept.Glitter;
    }
    return result;
  }
}
