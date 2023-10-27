package demo.wumpus.internal.events;

import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.figures.Wumpus;
import demo.wumpus.internal.WumpusWorld;

import java.util.ArrayList;
import java.util.List;

public class EatPlayer implements GameAction{
  private final Wumpus wumpus;

  public EatPlayer(Wumpus wumpus) {
    this.wumpus = wumpus;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    List<GameAction> followUp = new ArrayList<>();

    world.getFigures().stream()
        .filter(f -> f instanceof Player)
        .map(f -> (Player) f)
        .filter(w -> w.getPosition().equals(wumpus.getPosition()))
        .forEach(p -> {
          followUp.add(new RemoveFigure(p));
        });

    return followUp;
  }
}
