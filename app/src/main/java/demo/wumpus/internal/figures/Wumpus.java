package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.events.GameAction;
import demo.wumpus.internal.events.RemoveFigure;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Wumpus implements Perceptable, Figure {
  private final Room position;
  private boolean scream;
  private boolean alive;

  public Wumpus(Room position) {
    this.position = position;
    scream = false;
    alive = true;
  }

  @Override
  public Percept getPerceptFrom(Room room) {
    Collection<Room> stinkyPositions = position.getNeighbours();
    stinkyPositions.add(position);
    return stinkyPositions.contains(room) ? Percept.Stench : Percept.None;
  }

  public void die() {
    scream = true;
    alive = false;
  }

  public boolean isAlive() {
    return alive;
  }

  public Percept hadScream() {
    return scream ? Percept.Scream : Percept.None;
  }

  public void nextRound() {
    scream = false;
  }

  @Override
  public List<GameAction> takeAction(WumpusWorld world) {
    List<GameAction> followUp = new ArrayList<>();

    if(isAlive())
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
