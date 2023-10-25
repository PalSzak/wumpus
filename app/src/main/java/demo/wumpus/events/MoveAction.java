package demo.wumpus.events;

import demo.wumpus.Movable;
import demo.wumpus.Room;
import demo.wumpus.WumpusWorld;

public class MoveAction implements GameAction {

  private final Movable actor;

  public MoveAction(Movable actor) {
    this.actor = actor;
  }

  @Override
  public void run(WumpusWorld world) {
    Room destination = actor.getDestination();
    world.move(actor, destination);
  }
}
