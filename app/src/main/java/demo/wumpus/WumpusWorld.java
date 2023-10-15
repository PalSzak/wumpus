package demo.wumpus;

import java.util.List;

public class WumpusWorld {
  private static final int LOWER_COORD_BOUNDARY = -1;

  private final int upperCoordBoundary;
  private final Gold gold;
  private final Wumpi wumpi;

  public WumpusWorld(int size, Gold gold, Wumpi wumpi) {
    this.upperCoordBoundary = size;
    this.gold = gold;
    this.wumpi = wumpi;
  }

  public void move(Movable movable, Room moveTo) {
    if(LOWER_COORD_BOUNDARY < moveTo.getX() && moveTo.getX() < upperCoordBoundary &&
        LOWER_COORD_BOUNDARY < moveTo.getY() && moveTo.getY() < upperCoordBoundary) {
      movable.setPosition(moveTo);
    }
  }

  public List<Percept> getPerceptsOf(Player player) {
    return List.of(
        wumpi.getPerceptFrom(player.getPosition()),
        gold.getPerceptFrom(player.getPosition())
    );
  }
}
