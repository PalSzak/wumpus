package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.events.KillPlayer;
import demo.wumpus.internal.events.GameAction;

import java.util.List;

public class Pit implements Perceptable, Figure{
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

  @Override
  public List<GameAction> takeAction(List<Percept> percepts) {
    return List.of(new KillPlayer(this));
  }

  @Override
  public Room getPosition() {
    return position;
  }
}
