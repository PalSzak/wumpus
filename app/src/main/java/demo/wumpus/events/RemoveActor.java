package demo.wumpus.events;

import demo.wumpus.Actor;
import demo.wumpus.Arrow;
import demo.wumpus.WumpusWorld;

public class RemoveActor implements GameAction {
  private final Actor actor;

  public RemoveActor(Actor actor) {
    this.actor = actor;
  }

  @Override
  public void run(WumpusWorld world) {
    world.removeActor(actor);
  }
}
