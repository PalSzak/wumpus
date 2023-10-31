package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.figures.Wumpus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WumpusTest {

  @Test
  public void theWumpiIsStinkyInTheSameRoom() {
    Wumpus wumpus = new Wumpus(Room.of(5,5));
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(Room.of(5,5)), "Wumpus is stinky.");
  }

  @Test
  public void theWumpiIsStinkyInTheAdjectiveRooms() {
    Wumpus wumpus = new Wumpus(Room.of(5,5));
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(Room.of(6,5)), "Wumpus is stinky.");
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(Room.of(4,5)), "Wumpus is stinky.");
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(Room.of(5,4)), "Wumpus is stinky.");
    Assertions.assertEquals(Percept.Stench, wumpus.getPerceptFrom(Room.of(5,6)), "Wumpus is stinky.");
  }

  @Test
  public void theWumpiIsNotStinkyInOtherRooms() {
    Wumpus wumpus = new Wumpus(Room.of(5,5));
    Assertions.assertEquals(Percept.None, wumpus.getPerceptFrom(Room.START_POSITION), "Wumpus is stinky.");
  }

}