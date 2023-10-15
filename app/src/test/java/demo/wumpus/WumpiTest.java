package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WumpiTest {

  @Test
  public void theWumpiIsStinkyInTheSameRoom() {
    Wumpi wumpus = new Wumpi(new Room(5,5));
    Player player = new Player(new Room(5,5));
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(Room.START_POSITION), "Wumpus is stinky.");
  }


}