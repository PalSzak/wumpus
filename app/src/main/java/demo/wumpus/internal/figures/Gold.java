package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;

public class Gold implements Perceptable {

  private final Room position;

  public Gold(Room position) {
    this.position = position;
  }

  @Override
  public Percept getPerceptFrom(Room room) {
    Percept result = Percept.None;
    if(position.equals(room)){
      result = Percept.Glitter;
    }
    return result;
  }
}
