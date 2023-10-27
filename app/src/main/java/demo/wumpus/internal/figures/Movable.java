package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Room;

public interface Movable {
  Room getDestination();
  void setPosition(Room position);
  void bumpedToWall();
  Percept hadBump();
}
