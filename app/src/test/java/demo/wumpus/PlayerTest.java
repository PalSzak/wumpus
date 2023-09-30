package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

  @Test
  public void startPositionIs00() {
    Player player = new Player();
    Assertions.assertEquals(new ImmputableCoord(0,0), player.getPosition(), "Start postion is (0,0)");
  }
}
