package demo.wumpus;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Coord {
  public static final Coord ZERO_ZERO = new Coord(0,0);

  private final Integer x;
  private final Integer y;

  public Coord(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Coord getNeighbour(Direction.Directions direction) {
    return new Coord(x + direction.xOffset, y + direction.yOffset);
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Coord)) return false;

    Coord that = (Coord) o;

    return new EqualsBuilder().append(getX(), that.getX()).append(getY(), that.getY()).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(getX()).append(getY()).toHashCode();
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("x", x)
        .append("y", y)
        .toString();
  }
}
