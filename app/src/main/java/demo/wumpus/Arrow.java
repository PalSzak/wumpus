package demo.wumpus;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Arrow implements Movable {
  private final Direction.Directions directions;
  private Room position;

  public Arrow(Room position, Direction.Directions directions) {
    this.position = position;
    this.directions = directions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Arrow)) return false;

    Arrow arrow = (Arrow) o;

    return new EqualsBuilder().append(directions, arrow.directions).append(position, arrow.position).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(directions).append(position).toHashCode();
  }

  public Room move() {
    return position;
  }

  public void setPosition(Room position) {
    this.position = position;
  }

  @Override
  public void bumpedToWall() {

  }

  public Room getPosition() {
    return position;
  }
}
