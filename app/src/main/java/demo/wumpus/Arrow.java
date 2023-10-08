package demo.wumpus;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Arrow {
  private final Direction.Directions directions;
  private Coord position;

  public Arrow(Coord position, Direction.Directions directions) {
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

  public Coord move() {
    return position;
  }

  public void setPosition(Coord position) {
    this.position = position;
  }
}
