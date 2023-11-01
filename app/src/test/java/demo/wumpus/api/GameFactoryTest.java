package demo.wumpus.api;

import demo.wumpus.internal.GameImpl;
import demo.wumpus.internal.figures.Figure;
import demo.wumpus.internal.figures.Pit;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.figures.Wumpus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class GameFactoryTest {

  @Test
  public void basicGameRules() {
    GameImpl game = (GameImpl) new GameFactory().build();
    Assertions.assertEquals(Room.START_POSITION, game.getWorld().getFigures(Player.class).findFirst().get().getPosition(), "Start position is 0,0");
    Assertions.assertEquals(GameFactory.PIT_COUNT, game.getWorld().getFigures(Pit.class).count(), "Pit count is 3 x player count");
    Assertions.assertEquals(GameFactory.WUMPUS_COUNT, game.getWorld().getFigures(Wumpus.class).count(), "Wumpus count equals with player count");
    Assertions.assertEquals(1, maxFigureCountInRooms(game.getWorld().getFigures()),"Every object is in different cell");
    Assertions.assertTrue(figurePositionsAreWithinBoundaries(GameFactory.GRID_SIZE, game.getWorld().getFigures()), "Grid size is 3 x player count");
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
