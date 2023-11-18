package demo.wumpus.api;

import demo.wumpus.internal.GameImpl;
import demo.wumpus.internal.figures.*;
import demo.wumpus.internal.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameFactoryTest {

  @Test
  public void basicGameRules() {
    GameImpl game = (GameImpl) new GameFactory(new Agent() {
      @Override
      public Action makeMove(List<Percept> percepts) {
        return Action.NO_OP;
      }
    }).build();
    Assertions.assertEquals(Room.START_POSITION, game.getWorld().getFigures(Player.class).findFirst().get().getPosition(), "Start position is 0,0");
    Assertions.assertEquals(GameFactory.PIT_COUNT, game.getWorld().getFigures(Pit.class).count(), "Pit count is 3 x player count");
    Assertions.assertEquals(GameFactory.WUMPUS_COUNT, game.getWorld().getFigures(Wumpus.class).count(), "Wumpus count equals with player count");
    Assertions.assertEquals(1, maxFigureCountInRooms(game.getWorld().getFigures()),"Every object is in different cell");
    Assertions.assertTrue(figurePositionsAreWithinBoundaries(GameFactory.GRID_SIZE, game.getWorld().getFigures()), "Grid size is 3 x player count");
  }

/*
 Y
 ^
  ╔═╤═╤═╗
 2║P│P│ ║
  ╟─┼─┼─╢
 1║P│G│W║
  ╟─┼─┼─╢
 0║A│ │ ║
  ╚═╧═╧═╝
   0 1 2 > X
 */
  @Test
  public void sampleGame() {
    GameFactory gameFactory = new GameFactory(new Agent() {
      int currentRound = 0;

      @Override
      public Action makeMove(List<Percept> percepts) {
        return switch (++currentRound) {
          case 1 -> Action.TURN_RIGHT;
          case 2 -> Action.GO_FORWARD;
          case 3 -> Action.GO_FORWARD;
          case 4 -> Action.TURN_LEFT;
          case 5 -> Action.SHOOT;
          case 6 -> Action.GO_FORWARD;
          case 7 -> Action.TURN_LEFT;
          case 8 -> Action.GO_FORWARD;
          case 9 -> Action.GRAB;
          case 10 -> Action.TURN_LEFT;
          case 11 -> Action.GO_FORWARD;
          case 12 -> Action.TURN_RIGHT;
          case 13 -> Action.GO_FORWARD;
          case 14 -> Action.CLIMB;
          default -> Action.NO_OP;
        };
      }
    });

    gameFactory.random = new Random(24);
    GameImpl game = (GameImpl) gameFactory.build();
    game.run();
    Assertions.assertFalse(game.getWorld().getFigures(Wumpus.class).findFirst().get().isAlive());
    Assertions.assertEquals(0, game.getWorld().getFigures(Gold.class).count());
    Assertions.assertEquals(0, game.getWorld().getFigures(Player.class).count());
  }

  private boolean figurePositionsAreWithinBoundaries(int gridSize, Stream<Figure> figures) {
    return figures
        .flatMapToInt((figure -> IntStream.of(figure.getPosition().getX(), figure.getPosition().getY())))
        .max().getAsInt() < gridSize;
  }

  private int maxFigureCountInRooms(Stream<Figure> figures) {
    Map<Room, Integer> counter = new HashMap<>();
    figures.forEach(f -> {
      counter.compute(
          f.getPosition(),
          (pos, currCount) -> Objects.isNull(currCount) ? 1 : currCount + 1);
    });
    return counter.values().stream().max(Integer::compareTo).get();
  }


}
