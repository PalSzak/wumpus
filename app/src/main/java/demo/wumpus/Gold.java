package demo.wumpus;

public class Gold {

  public Gold(Coord coord) {
  }

  public Percept getPercept(Player player) {
    return Percept.Glitter;
  }
}
