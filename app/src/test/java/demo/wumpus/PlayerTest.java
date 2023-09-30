package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  public void startPositionIs00() {
    Player player = new Player();
    Assertions.assertEquals(new ImmputableCoord(0,0), player.getPosition(), "Start position is (0,0)");
  }

  @Test
  public void playerCanMove() {
    Player player = new Player();
    Assertions.assertEquals(new ImmputableCoord(1,0), player.move(), "Player steps to it's direction");
  }
}
