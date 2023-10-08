package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldTest {

  @Test
  public void goldIsGLittering() {
    Gold gold = new Gold();
    Assertions.assertEquals(Precepts.Glitter, gold.getPrecept());
  }
}