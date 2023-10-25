package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PlayerTest {
  private Player player;
  private Room currentPlayerCoord;

  @BeforeEach
  public void init() {
    currentPlayerCoord = new Room(5, 5);
    player = new Player(currentPlayerCoord);
  }

  @Test
  public void playerCanMove() {
    Assertions.assertEquals(new Room(6,5), player.getDestination(), "Player steps to it's direction");
  }

  @Test
  public void playerCanTurnLeft() {
    player.turnLeft();
    Assertions.assertEquals(new Room(5,4), player.getDestination(), "Player steps to it's direction");
  }

  @Test
  public void playerCanTurnRight() {
    player.turnRight();
    Assertions.assertEquals(new Room(5,6), player.getDestination(), "Player steps to it's direction");
  }

  @Test
  public void playerShootsToFaceDirection() {
    Optional<Arrow> arrow = player.shoot();
    Assertions.assertEquals(new Arrow(currentPlayerCoord, Direction.Directions.Up), arrow.get(), "Arrow should file to player direction");
  }

  @Test
  public void playerCanShootOnlyOnce() {
    player.shoot();
    Optional<Arrow> arrow = player.shoot();
    Assertions.assertTrue(arrow.isEmpty(), "Player has only one arrow");
  }
}
