package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoldTest {

  @Test
  public void goldIsGLittering() {
    Gold gold = new Gold(Coord.ZERO_ZERO);
    Assertions.assertEquals(Percept.Glitter, gold.getPercept());
  }
}
