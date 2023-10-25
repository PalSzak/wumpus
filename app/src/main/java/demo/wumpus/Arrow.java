package demo.wumpus;

import demo.wumpus.events.GameAction;
import demo.wumpus.events.MoveAction;
import demo.wumpus.events.RemoveActor;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;
import java.util.Optional;

public class Arrow implements Movable, Actor {
  private final Direction.Directions direction;
  private Room position;
  private boolean bumpedToWall;

  public Arrow(Room position, Direction.Directions directions) {
    this.position = position;
    this.direction = directions;
    this.bumpedToWall = false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof Arrow)) return false;

    Arrow arrow = (Arrow) o;

    return new EqualsBuilder().append(direction, arrow.direction).append(position, arrow.position).isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(direction).append(position).toHashCode();
  }

  @Override
  public Room getDestination() {
    return position.getNeighbour(direction);
  }

  public void setPosition(Room position) {
    this.position = position;
  }

  @Override
  public void bumpedToWall() {
    this.bumpedToWall = true;
  }

  public Room getPosition() {
    return position;
  }

  @Override
  public boolean hadBump() {
    return bumpedToWall;
  }

  @Override
  public Optional<GameAction> takeAction(List<Percept> percepts) {
    return Optional.of(percepts.contains(Percept.Bump)
        ? new RemoveActor(this)
        : new MoveAction(this));
  }
}
