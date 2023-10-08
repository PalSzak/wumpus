package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WumpiTest {

  @Test
  public void theWumpiIsStinkyInTheSameRoom() {
    Wumpi wumpus = new Wumpi(Room.START_POSITION);
    Player player = new Player(Room.START_POSITION);
    Assertions.assertEquals(Percept.Stench, wumpus.getPrecept(), "Wumpus is stinky.");
  }


}