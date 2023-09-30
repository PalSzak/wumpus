package demo.wumpus;

public class Player {
  ImmputableCoord position;
  Direction faceDirection;

  public Player(ImmputableCoord startPosition) {
    position = startPosition;
    faceDirection = new Direction();
  }

  public ImmputableCoord move() {
    return position.getNeighbour(faceDirection.getCurrentDirection());
  }

  public void turnLeft() {
    faceDirection.turnLeft();
  }
}
