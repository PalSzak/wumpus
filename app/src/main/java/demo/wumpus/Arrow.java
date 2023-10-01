package demo.wumpus;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Arrow {
  private final Direction.Directions directions;
  private final Coord currentPlayerCoord;

  public Arrow(Coord currentPlayerCoord, Direction.Directions directions) {
    this.currentPlayerCoord = currentPlayerCoord;
    this.directions = directions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Arrow)) return false;

    Arrow arrow = (Arrow) o;

    return new EqualsBuilder().append(directions, arrow.directions).append(currentPlayerCoord, arrow.currentPlayerCoord).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(directions).append(currentPlayerCoord).toHashCode();
  }
}
