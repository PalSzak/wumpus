package demo.wumpus.internal;

import demo.wumpus.api.Action;
import demo.wumpus.api.Agent;
import demo.wumpus.api.Percept;
import demo.wumpus.internal.figures.Arrow;
import demo.wumpus.internal.figures.Gold;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.figures.Wumpus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ActionTest {

  @Test
  public void goForward() {
    GameImpl game = buildGame(new Agent() {
      @Override
      public Action makeMove(List<Percept> percepts) {
        return Action.GO_FORWARD;
      }
    });

    game.nextRound();

    Player player = game.getWorld().getFigures(Player.class).findAny().get();
    Assertions.assertEquals(Room.of(5,6), player.getPosition(), "Go Forward");
  }

  @Test
  void turnLeft() {
    GameImpl game = buildGame(new Agent() {
      int i = 0;
      @Override
      public Action makeMove(List<Percept> percepts) {
        return switch (++i) {
          case 1 -> Action.TURN_LEFT;
          case 2 -> Action.GO_FORWARD;
          default -> Action.NO_OP;
        };
      }
    });

    game.nextRound();
    game.nextRound();

    Player player = game.getWorld().getFigures(Player.class).findAny().get();
    Assertions.assertEquals(Room.of(4,5), player.getPosition(), "Turn Left");
  }

  @Test
  void turnRight() {
    GameImpl game = buildGame(new Agent() {
      int i = 0;
      @Override
      public Action makeMove(List<Percept> percepts) {
        return switch (++i) {
          case 1 -> Action.TURN_RIGHT;
          case 2 -> Action.GO_FORWARD;
          default -> Action.NO_OP;
        };
      }
    });

    game.nextRound();
    game.nextRound();

    Player player = game.getWorld().getFigures(Player.class).findAny().get();
    Assertions.assertEquals(Room.of(6,5), player.getPosition(), "Turn Right");
  }

  @Test
  void canShoot() {
    GameImpl game = buildGame(new Agent() {
      @Override
      public Action makeMove(List<Percept> percepts) {
        return Action.SHOOT;
      }
    });

    game.nextRound();

    Optional<Arrow> arrow = game.getWorld().getFigures(Arrow.class).findAny();
    Assertions.assertTrue(arrow.isPresent(), "Arrow shoot");
  }

  private GameImpl buildGame(Agent agent) {
    return new GameImpl(
        new Player(
            Room.of(5, 5),
            agent),
        new WumpusWorld(
            10,
            new Gold(Room.START_POSITION),
            new Wumpus(Room.START_POSITION),
            Collections.emptyList()
        )
    );
  }
}
