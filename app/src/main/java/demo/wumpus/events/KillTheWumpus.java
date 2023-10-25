package demo.wumpus.events;

import demo.wumpus.Arrow;
import demo.wumpus.Figure;
import demo.wumpus.Wumpus;
import demo.wumpus.WumpusWorld;

import java.util.Optional;

public class KillTheWumpus implements GameAction{
  private final Figure arrow;

  public KillTheWumpus(Arrow arrow) {
    this.arrow = arrow;
  }

  @Override
  public void run(WumpusWorld world) {
    Optional<Wumpus> prey = world.getFigures().stream()
        .filter(f -> f instanceof Wumpus)
        .map(f -> (Wumpus) f)
        .filter(w -> w.getPosition().equals(arrow.getPosition()))
        .findFirst();

    if(prey.isPresent()){
      prey.get().die();
    }
  }
}
