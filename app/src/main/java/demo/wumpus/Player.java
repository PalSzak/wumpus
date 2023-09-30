package demo.wumpus;

public class Player {
  ImmputableCoord position;
  Direction direction;

  public Player() {
    position = new ImmputableCoord(0, 0);
    direction = new Direction();
  }

  public ImmputableCoord getPosition() {
    return position;
  }

  public void move() {
    position = position.getNeighbour(direction.getCurrentDirection());
  }
}
