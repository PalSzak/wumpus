package demo.wumpus.api;

import demo.wumpus.internal.GameImpl;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameFactoryTest {

  @Test
  public void basicGameRules() {
    GameImpl game = (GameImpl) new GameFactory().build();
    Assertions.assertEquals(Room.of(0,0), game.getWorld().getFigures(Player.class).findFirst().get().getPosition(), "Start position is 0,0");
  }


}
