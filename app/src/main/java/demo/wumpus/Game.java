package demo.wumpus;

import demo.wumpus.events.GameAction;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Game {
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
    for(Figure figure : world.getFigures()) {
      Optional<GameAction> gameAction = figure.takeAction(world.getPerceptsOf(figure));

      if(gameAction.isPresent())
        roundActionStack.add(gameAction.get());
    }

    while (!roundActionStack.isEmpty())
      roundActionStack.poll().run(world);
  }
}
