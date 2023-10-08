package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WumpiTest {

  @Test
  public void theWumpusIsStinky() {
    Wumpi wumpus = new Wumpi();
    Assertions.assertEquals(Percept.Stench, wumpus.getPrecept(), "Wumpus is stinky.");
  }


}