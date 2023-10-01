package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
  private Player player;
  private Coord currentPlayerCoord;

  @BeforeEach
  public void init() {
    currentPlayerCoord = new Coord(5, 5);
    player = new Player(currentPlayerCoord);
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

  @Test
  public void playerShootsToFaceDirection() {
    Arrow arrow = player.shoot();
    Assertions.assertEquals(new Arrow(currentPlayerCoord, Direction.Directions.Up), arrow, "Arrow should file to player direction");
  }

}
