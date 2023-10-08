package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PitTest {

  @Test
  public void pitIsWindy(){
    Pit pit = new Pit();
    Assertions.assertEquals(Percept.Breeze,pit.getPrecept(), "Pit is windy");
  }
}