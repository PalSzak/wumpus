package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

  @Test
  public void defaultDirectionIsUp() {
    Direction direction = new Direction();
    Assertions.assertEquals(Direction.Directions.Up, direction.getCurrentDirection(), "Default direction is Up");
  }

  @Test
  public void canTurnLeft() {
    Direction direction = new Direction();
    direction.turnLeft();
    Assertions.assertEquals(Direction.Directions.Left, direction.getCurrentDirection());
  }

  @Test
  public void doubleLeftIsDown() {
    Direction direction = new Direction();
    direction.turnLeft();
    direction.turnLeft();
    Assertions.assertEquals(Direction.Directions.Down, direction.getCurrentDirection());
  }
}
