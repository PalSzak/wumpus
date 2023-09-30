package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DirectionTest {

  @Test
  public void defaultDirectionIsUp() {
    Direction direction = new Direction();
    Assertions.assertEquals(Direction.Directions.Up, direction.getCurrentDirection(), "Default direction is Up");
  }

}
