package demo.wumpus;

import demo.wumpus.events.GameAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Game {
  private WumpusWorld world;
  List<Actor> actors;

  public Game(Player player, WumpusWorld world) {
    this.world = world;
    actors = new ArrayList<>();
    actors.add(player);
  }

  protected void addActor(Arrow arrow) {
    actors.add(arrow);
  }

  protected void nextRound(){
    for(Actor actor : actors) {
      Optional<GameAction> gameAction = actor.takeAction(world.getPerceptsOf(actor));
      if(gameAction.isPresent())
        gameAction.get().run(world);
    }
  }
}
