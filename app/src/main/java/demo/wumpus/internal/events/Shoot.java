package demo.wumpus.internal.events;

import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.figures.Arrow;
import demo.wumpus.internal.figures.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Shoot implements GameAction {
  private final Player player;

  public Shoot(Player player) {
    this.player = player;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    List<GameAction> followUp = new ArrayList<>();
    Optional<Arrow> arrow = player.shoot();

    if(arrow.isPresent()){
      world.addFigure(arrow.get());
      followUp.addAll(arrow.get().takeAction(world));
    }

    return followUp;
  }
}
