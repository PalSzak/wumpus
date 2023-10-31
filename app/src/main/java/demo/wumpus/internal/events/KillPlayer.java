package demo.wumpus.internal.events;

import demo.wumpus.internal.figures.Figure;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.WumpusWorld;

import java.util.ArrayList;
import java.util.List;

public class KillPlayer implements GameAction {
  private final Figure figure;

  public KillPlayer(Figure figure) {
    this.figure = figure;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    List<GameAction> followUp = new ArrayList<>();

    world.getFigures(Player.class)
        .filter(p -> p.getPosition().equals(figure.getPosition()))
        .forEach(p -> {
          followUp.add(new RemoveFigure(p));
        });

    return followUp;
  }
}
