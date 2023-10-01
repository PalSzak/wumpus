package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WumpusTest {

  @Test
  public void theWumpusIsStinky() {
    Wumpus wumpus = new Wumpus();
    Assertions.assertEquals(Precepts.Stench, wumpus.getPrecept(), "Wumpus is stinky.");
  }


}