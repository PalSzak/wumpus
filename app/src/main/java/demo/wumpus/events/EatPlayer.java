package demo.wumpus.events;

import demo.wumpus.Player;
import demo.wumpus.Wumpus;
import demo.wumpus.WumpusWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EatPlayer implements GameAction{
  private final Wumpus wumpus;

  public EatPlayer(Wumpus wumpus) {
    this.wumpus = wumpus;
  }

  @Override
  public List<GameAction> run(WumpusWorld world) {
    List<GameAction> followUp = new ArrayList<>();

    world.getFigures().stream()
        .filter(f -> f instanceof Player)
        .map(f -> (Player) f)
        .filter(w -> w.getPosition().equals(wumpus.getPosition()))
        .forEach(p -> {
          followUp.add(new RemoveFigure(p));
        });

    return followUp;
  }
}
