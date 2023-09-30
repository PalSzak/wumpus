package demo.wumpus;

import java.util.List;

public class Direction {

  public enum Directions {
    Up, Right, Down, Left;
  }

  private List<Directions> directions =
      List.of(
          Directions.Up,
          Directions.Right,
          Directions.Down,
          Directions.Left);

  private int currDirIdx;

  public Direction() {
    currDirIdx = 0;
  }

  public Directions getCurrentDirection() {
    return directions.get(currDirIdx);
  }

  public void turnLeft() {
    currDirIdx = (currDirIdx + 3) % directions.size();
  }

  public void turnRight() {
    currDirIdx = (currDirIdx + 1) % directions.size();
  }

}
