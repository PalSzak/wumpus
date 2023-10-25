package demo.wumpus;

public interface Movable {

  Room getDestination();
  void setPosition(Room position);
  void bumpedToWall();
}
