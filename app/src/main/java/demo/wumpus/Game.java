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
    world.addActor(player);
  }

  protected void addActor(Arrow arrow) {
    world.addActor(arrow);
  }

  protected void nextRound(){
    for(Actor actor : world.getActors()) {
      Optional<GameAction> gameAction = actor.takeAction(world.getPerceptsOf(actor));

      if(gameAction.isPresent())
        roundActionStack.add(gameAction.get());
    }

    while (!roundActionStack.isEmpty())
      roundActionStack.poll().run(world);
  }
}
