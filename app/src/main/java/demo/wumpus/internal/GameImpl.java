package demo.wumpus.internal;

import demo.wumpus.api.Game;
import demo.wumpus.internal.events.GameAction;
import demo.wumpus.internal.figures.Arrow;
import demo.wumpus.internal.figures.Figure;
import demo.wumpus.internal.figures.Player;

import java.util.LinkedList;
import java.util.Queue;

public class GameImpl implements Game {
  private Queue<GameAction> roundActionStack;
  private WumpusWorld world;

  public GameImpl(Player player, WumpusWorld world) {
    roundActionStack = new LinkedList<>();
    this.world = world;
    world.addFigure(player);
  }

  protected void addFigure(Arrow arrow) {
    world.addFigure(arrow);
  }

  protected void nextRound(){
    world.getFigures(Figure.class).forEach( figure ->
        roundActionStack.addAll(figure.takeAction(world.getPerceptsOf(figure)))
    );

    while (!roundActionStack.isEmpty())
      roundActionStack.addAll(roundActionStack.poll().run(world));
  }

  @Override
  public void run() {
    while (isPlayerOnBoard())
      nextRound();
  }

  private boolean isPlayerOnBoard() {
    return world.getFigures(Player.class).findAny().isPresent();
  }

  public WumpusWorld getWorld() {
    return world;
  }
}
