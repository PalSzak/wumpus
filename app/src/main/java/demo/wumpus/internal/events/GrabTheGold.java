package demo.wumpus.internal.events;

import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.figures.Gold;
import demo.wumpus.internal.figures.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class GrabTheGold implements GameAction{
  private final Player player;

  public GrabTheGold(Player player) {
    this.player = player;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    ArrayList<GameAction> followUp = new ArrayList<>();

    Optional<Gold> gold = world.getFigures(Gold.class).findFirst();
    if(gold.isPresent() && gold.get().getPosition().equals(player.getPosition())) {
      followUp.add(new RemoveFigure(gold.get()));
    }

    return followUp;
  }
}
