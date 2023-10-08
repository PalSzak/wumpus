package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PitTest {

  @Test
  public void pitCausesWindInNeighbourRooms(){
    Pit pit = new Pit(new Room(5,5));
    Player upperNeighbour = new Player(new Room(6, 5));
    Player lowerNeighbour = new Player(new Room(4, 5));
    Player leftNeighbour = new Player(new Room(5, 4));
    Player rightNeighbour = new Player(new Room(5, 4));

    Assertions.assertEquals(Percept.Breeze,pit.getPercept(upperNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPercept(lowerNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPercept(leftNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPercept(rightNeighbour), "Pit is windy");
  }

}