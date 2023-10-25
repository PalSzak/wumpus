package demo.wumpus.events;

import demo.wumpus.Figure;
import demo.wumpus.WumpusWorld;

public class RemoveFigure implements GameAction {
  private final Figure figure;

  public RemoveFigure(Figure figure) {
    this.figure = figure;
  }

  @Override
  public void run(WumpusWorld world) {
    world.removeFigure(figure);
  }
}
