package demo.wumpus;

import java.util.Optional;

public class Player implements Movable{
  private boolean hasArrow;
  private Room position;
  private final Direction faceDirection;
  private boolean bumpedToWall;

  public Player(Room startPosition) {
    position = startPosition;
    faceDirection = new Direction();
    hasArrow = true;
    bumpedToWall = false;
  }

  public Room move() {
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

  public void setPosition(Room position) {
    this.position = position;
  }

  @Override
  public void bumpedToWall() {
    bumpedToWall = true;
  }

  public Room getPosition() {
    return position;
  }

  public boolean hadBump() {
    return bumpedToWall;
  }

  public void nextRound() {
    bumpedToWall = false;
  }
}
