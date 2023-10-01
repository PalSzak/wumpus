package demo.wumpus;

public class Player {
  Coord position;
  Direction faceDirection;

  public Player(Coord startPosition) {
    position = startPosition;
    faceDirection = new Direction();
  }

  public Coord move() {
    return position.getNeighbour(faceDirection.getCurrentDirection());
  }

  public void turnLeft() {
    faceDirection.turnLeft();
  }

  public void turnRight() {
    faceDirection.turnRight();
  }

  public Arrow shoot() {
    return new Arrow(position, faceDirection.getCurrentDirection());
  }
}
