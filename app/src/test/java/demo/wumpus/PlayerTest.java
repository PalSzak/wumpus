package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  Player player;

  @BeforeEach
  public void init() {
    player = new Player(new Coord(5, 5));
  }

  @Test
  public void playerCanMove() {
    Assertions.assertEquals(new Coord(6,5), player.move(), "Player steps to it's direction");
  }

  @Test
  public void playerCanTurnLeft() {
    player.turnLeft();
    Assertions.assertEquals(new Coord(5,4), player.move(), "Player steps to it's direction");
  }

  @Test
  public void playerCanTurnRight() {
    player.turnRight();
    Assertions.assertEquals(new Coord(5,6), player.move(), "Player steps to it's direction");
  }


}
