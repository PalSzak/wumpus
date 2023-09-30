package demo.wumpus;

public class Direction {
  public enum Directions {
    Up, Right, Down, Left;
  }

  private Directions currentDirection;

  public Direction() {
    currentDirection = Directions.Up;
  }

  public Directions getCurrentDirection() {
    return currentDirection;
  }


}
