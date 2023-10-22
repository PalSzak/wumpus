package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameFactoryTest {

  @Test
  public void basicGameRules() {
    Game game = new GameFactory().build();
    Assertions.assertEquals(new Room(0, 0), game.getPlayer().getPosition(), "Start position is 0,0");
  }


}
