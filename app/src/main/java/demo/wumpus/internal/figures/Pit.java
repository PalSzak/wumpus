package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.events.GameAction;
import demo.wumpus.internal.events.RemoveFigure;

import java.util.ArrayList;
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
  public List<GameAction> takeAction(WumpusWorld world) {
    List<GameAction> followUp = new ArrayList<>();

    world.getFigures(Player.class)
        .filter(p -> p.getPosition().equals(getPosition()))
        .forEach(p -> {
          followUp.add(new RemoveFigure(p));
        });

    return followUp;
  }

  @Override
  public Room getPosition() {
    return position;
  }
}
