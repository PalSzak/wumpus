package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GoldTest {

  @Test
  public void goldIsGLitteringInTheSameRoom() {
    Gold gold = new Gold(Coord.ZERO_ZERO);
    Assertions.assertEquals(Percept.Glitter, gold.getPercept(new Player(Coord.ZERO_ZERO)));
  }

  @Test
  public void goldIsNotPerceptibleFromOtherRooms() {
    Gold gold = new Gold(Coord.ZERO_ZERO);
    Assertions.assertEquals(Percept.None, gold.getPercept(new Player(new Coord(1,1))));
  }
}
