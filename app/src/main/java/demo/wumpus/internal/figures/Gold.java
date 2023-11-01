package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.events.GameAction;

import java.util.Collections;
import java.util.List;

public class Gold implements Perceptable, Figure {

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

  @Override
  public List<GameAction> takeAction(List<Percept> percepts) {
    return Collections.emptyList();
  }

  @Override
  public Room getPosition() {
    return position;
  }
}
