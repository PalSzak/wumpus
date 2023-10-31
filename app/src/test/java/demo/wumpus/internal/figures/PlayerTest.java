package demo.wumpus.internal.figures;

import demo.wumpus.internal.figures.Arrow;
import demo.wumpus.internal.Direction;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class PlayerTest {
  private Player player;
  private Room currentPlayerCoord;

  @BeforeEach
  public void init() {
    currentPlayerCoord = Room.of(5, 5);
    player = new Player(currentPlayerCoord);
  }

  @Test
  public void playerCanMove() {
    Assertions.assertEquals(Room.of(6,5), player.getDestination(), "Player steps to it's direction");
  }

  @Test
  public void playerCanTurnLeft() {
    player.turnLeft();
    Assertions.assertEquals(Room.of(5,4), player.getDestination(), "Player steps to it's direction");
  }

  @Test
  public void playerCanTurnRight() {
    player.turnRight();
    Assertions.assertEquals(Room.of(5,6), player.getDestination(), "Player steps to it's direction");
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
