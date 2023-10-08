package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoldTest {

  @Test
  public void goldIsGLitteringInTheSameRoom() {
    Gold gold = new Gold(Room.START_POSITION);
    Assertions.assertEquals(Percept.Glitter, gold.getPercept(new Player(Room.START_POSITION)));
  }

  @Test
  public void goldIsNotPerceptibleFromOtherRooms() {
    Gold gold = new Gold(Room.START_POSITION);
    Assertions.assertEquals(Percept.None, gold.getPercept(new Player(new Room(1,1))));
  }
}
