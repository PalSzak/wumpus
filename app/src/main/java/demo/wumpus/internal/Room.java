package demo.wumpus.internal;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
  private static final Map<Integer, Room> CACHE = new HashMap<>();
  public static final Room START_POSITION = Room.of(0,0);

  public static Room of(int x, int y) {
    return CACHE.computeIfAbsent(cantorPairing(x,y), k -> new Room(x,y));
  }

  private static Integer cantorPairing(int a, int b) {
    return (a + b) * (a + b + 1) / 2 + a;
  }

  private final Integer x;
  private final Integer y;

  private Room(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  public Room getNeighbour(Direction.Directions direction) {
    return Room.of(x + direction.xOffset, y + direction.yOffset);
  }

  public Collection<Room> getNeighbours() {
    return Arrays.stream(Direction.Directions.values()).map(this::getNeighbour).collect(Collectors.toList());
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
}
