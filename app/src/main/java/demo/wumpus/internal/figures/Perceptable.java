package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;

public interface Perceptable {

  Percept getPerceptFrom(Room room);
}
