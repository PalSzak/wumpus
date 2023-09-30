package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  Player player;

  @BeforeEach
  public void init() {
    player = new Player(new ImmputableCoord(5, 5));
  }

  @Test
  public void playerCanMove() {
    Assertions.assertEquals(new ImmputableCoord(6,5), player.move(), "Player steps to it's direction");
  }

  @Test
  public void playerCanTurnLeft() {
    player.turnLeft();
    Assertions.assertEquals(new ImmputableCoord(5,4), player.move(), "Player steps to it's direction");
  }

}
