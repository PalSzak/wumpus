package demo.wumpus.internal.events;

import demo.wumpus.internal.figures.Figure;
import demo.wumpus.internal.WumpusWorld;

import java.util.Collections;
import java.util.List;

public class RemoveFigure implements GameAction {
  private final Figure figure;

  public RemoveFigure(Figure figure) {
    this.figure = figure;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    world.removeFigure(figure);
    return Collections.emptyList();
  }
}
