package demo.wumpus;

import java.util.Optional;

public class Player implements Movable{
  private boolean hasArrow;
  private Coord position;
  private final Direction faceDirection;

  public Player(Coord startPosition) {
    position = startPosition;
    faceDirection = new Direction();
    hasArrow = true;
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

  public Optional<Arrow> shoot() {
    if(hasArrow) {
      hasArrow = false;
      return Optional.of(new Arrow(position, faceDirection.getCurrentDirection()));
    } else {
      return Optional.empty();
    }
  }

  public void setPosition(Coord position) {
    this.position = position;
  }

  public Coord getPosition() {
    return position;
  }
}
