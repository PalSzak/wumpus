package demo.wumpus.internal.events;

import demo.wumpus.internal.figures.Movable;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;

import java.util.Collections;
import java.util.List;

public class MoveAction implements GameAction {

  private final Movable actor;

  public MoveAction(Movable actor) {
    this.actor = actor;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    Room destination = actor.getDestination();
    world.move(actor, destination);
    return Collections.emptyList();
  }
}
