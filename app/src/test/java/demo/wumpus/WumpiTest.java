package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WumpiTest {

  @Test
  public void theWumpiIsStinkyInTheSameRoom() {
    Wumpi wumpus = new Wumpi(new Room(5,5));
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(new Room(5,5)), "Wumpus is stinky.");
  }

  @Test
  public void theWumpiIsStinkyInTheAdjectiveRooms() {
    Wumpi wumpus = new Wumpi(new Room(5,5));
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(new Room(6,5)), "Wumpus is stinky.");
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(new Room(4,5)), "Wumpus is stinky.");
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(new Room(5,4)), "Wumpus is stinky.");
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(new Room(5,6)), "Wumpus is stinky.");
  }

  @Test
  public void theWumpiIsNotStinkyInOtherRooms() {
    Wumpi wumpus = new Wumpi(new Room(5,5));
    Assertions.assertEquals(Percept.None, wumpus.getPerceptFrom(Room.START_POSITION), "Wumpus is stinky.");
  }

}