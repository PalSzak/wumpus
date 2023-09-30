package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DirectionTest {

  Direction direction;

  @BeforeEach
  public void init() {
    direction = new Direction();
  }


  @Test
  public void defaultDirectionIsUp() {
    Assertions.assertEquals(Direction.Directions.Up, direction.getCurrentDirection(), "Default direction is Up");
  }

  @Test
  public void canTurnLeft() {
    direction.turnLeft();
    Assertions.assertEquals(Direction.Directions.Left, direction.getCurrentDirection(), "Player can turn left");
  }

  @Test
  public void doubleLeftIsDown() {
    direction.turnLeft();
    direction.turnLeft();
    Assertions.assertEquals(Direction.Directions.Down, direction.getCurrentDirection());
  }

  @Test
  public void canTurnRight() {
    direction.turnRight();
    Assertions.assertEquals(Direction.Directions.Right, direction.getCurrentDirection(), "Player can turn right");
  }
}
