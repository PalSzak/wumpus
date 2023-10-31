package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.figures.Pit;
import demo.wumpus.internal.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PitTest {

  @Test
  public void pitCausesWindInNeighbourRooms(){
    Pit pit = new Pit(Room.of(5,5));
    Room upperNeighbour = Room.of(6, 5);
    Room lowerNeighbour = Room.of(4, 5);
    Room leftNeighbour = Room.of(5, 4);
    Room rightNeighbour = Room.of(5, 4);

    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(upperNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(lowerNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(leftNeighbour), "Pit is windy");
    Assertions.assertEquals(Percept.Breeze,pit.getPerceptFrom(rightNeighbour), "Pit is windy");
  }

  @Test
  public void pitIsNotPerceptableFromFarCells(){
    Pit pit = new Pit(Room.of(5,5));
    Room distantRoom = Room.START_POSITION;

    Assertions.assertEquals(Percept.None,pit.getPerceptFrom(distantRoom), "No wind from there");
  }
}