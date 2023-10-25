package demo.wumpus.events;

import demo.wumpus.Figure;
import demo.wumpus.WumpusWorld;

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
