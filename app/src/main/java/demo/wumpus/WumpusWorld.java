package demo.wumpus;

public class WumpusWorld {

  public void move(Movable movable, Coord moveTo) {
    movable.setPosition(moveTo);
  }

}
