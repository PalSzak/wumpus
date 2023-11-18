package demo.wumpus.internal.figures;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.Direction;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.events.GameAction;
import demo.wumpus.internal.events.KillTheWumpus;
import demo.wumpus.internal.events.MoveAction;
import demo.wumpus.internal.events.RemoveFigure;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class Arrow implements Movable, Figure {
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
  public Percept hadBump() {
    return bumpedToWall ? Percept.Bump : Percept.None;
  }

  @Override
  public List<GameAction> takeAction(WumpusWorld world) {
    return Percept.Bump.equals(hadBump())
        ? List.of(new RemoveFigure(this))
        : List.of(new MoveAction(this), new KillTheWumpus(this));
  }
}
