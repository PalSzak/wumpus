package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PitTest {

  @Test
  public void pitCausesWindInNeighbourRooms(){
    Pit pit = new Pit(new Room(5,5));
    Room upperNeighbour = new Room(6, 5);
    Room lowerNeighbour = new Room(4, 5);
    Room leftNeighbour = new Room(5, 4);
    Room rightNeighbour = new Room(5, 4);

    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(upperNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(lowerNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(leftNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(rightNeighbour), "Pit is windy");
  }

  @Test
  public void pitIsNotPerceptableFromFarCells(){
    Pit pit = new Pit(new Room(5,5));
    Room distantRoom = Room.START_POSITION;

    Assertions.assertEquals(Percept.None,pit.getPerceptFrom(distantRoom), "No wind from there");
  }
}