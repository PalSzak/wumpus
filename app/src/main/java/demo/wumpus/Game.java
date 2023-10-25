package demo.wumpus;

import demo.wumpus.events.GameAction;

import java.util.LinkedList;
import java.util.Queue;

public class Game implements Runnable {
  private Queue<GameAction> roundActionStack;
  WumpusWorld world;

  public Game(Player player, WumpusWorld world) {
    roundActionStack = new LinkedList<>();
    this.world = world;
    world.addFigure(player);
  }

  protected void addFigure(Arrow arrow) {
    world.addFigure(arrow);
  }

  protected void nextRound(){
    for(Figure figure : world.getFigures())
      roundActionStack.addAll(figure.takeAction(world.getPerceptsOf(figure)));

    while (!roundActionStack.isEmpty())
      roundActionStack.addAll(roundActionStack.poll().run(world));
  }

  @Override
  public void run() {
    while (isPlayerOnBoard())
      nextRound();
  }

  private boolean isPlayerOnBoard() {
    return world.getFigures().stream()
        .anyMatch( f -> f instanceof Player);
  }
}
