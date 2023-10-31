package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.figures.Gold;
import demo.wumpus.internal.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoldTest {

  @Test
  public void goldIsGLitteringInTheSameRoom() {
    Gold gold = new Gold(Room.START_POSITION);
    Assertions.assertEquals(Percept.Glitter, gold.getPerceptFrom(Room.START_POSITION));
  }

  @Test
  public void goldIsNotPerceptibleFromOtherRooms() {
    Gold gold = new Gold(Room.START_POSITION);
    Assertions.assertEquals(Percept.None, gold.getPerceptFrom(Room.of(1,1)));
  }
}
