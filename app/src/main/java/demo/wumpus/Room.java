package demo.wumpus;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Room {
  public static final Room START_POSITION = new Room(0,0);

  private final Integer x;
  private final Integer y;

  public Room(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Room getNeighbour(Direction.Directions direction) {
    return new Room(x + direction.xOffset, y + direction.yOffset);
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

    if (!(o instanceof Room)) return false;

    Room that = (Room) o;

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

  public Collection<Object> getNeighbours() {
    return Arrays.stream(Direction.Directions.values()).map(this::getNeighbour).collect(Collectors.toList());
  }
}
