package demo.wumpus.internal;

import java.util.List;

public class Direction {

  public enum Directions {
    Up(1, 0),
    Right(0, 1),
    Down(-1, 0),
    Left(0, -1);

    public Integer xOffset;
    public Integer yOffset;

    Directions(Integer xOffset, Integer yOffset) {
      this.xOffset = xOffset;
      this.yOffset = yOffset;
    }
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
