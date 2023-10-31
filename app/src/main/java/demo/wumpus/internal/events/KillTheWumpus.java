package demo.wumpus.internal.events;

import demo.wumpus.internal.figures.Arrow;
import demo.wumpus.internal.figures.Figure;
import demo.wumpus.internal.figures.Wumpus;
import demo.wumpus.internal.WumpusWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KillTheWumpus implements GameAction{
  private final Figure arrow;

  public KillTheWumpus(Arrow arrow) {
    this.arrow = arrow;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    List<GameAction> followUp = new ArrayList<>();

    Optional<Wumpus> prey = world.getFigures(Wumpus.class)
        .filter(w -> w.getPosition().equals(arrow.getPosition()))
        .findFirst();

    if(prey.isPresent()){
      prey.get().die();
      followUp.add(new RemoveFigure(arrow));
    }

    return followUp;
  }
}
