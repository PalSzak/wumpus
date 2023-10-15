package demo.wumpus;

import java.util.List;

public class WumpusWorld {
  private static final int LOWER_COORD_BOUNDARY = -1;

  private final int upperCoordBoundary;
  private final Gold gold;
  private final Wumpi wumpi;
  private final List<Pit> pits;

  public WumpusWorld(int size, Gold gold, Wumpi wumpi, List<Pit> pits) {
    this.upperCoordBoundary = size;
    this.gold = gold;
    this.wumpi = wumpi;
    this.pits = pits;
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
        pits.stream()
            .map(pit -> pit.getPerceptFrom(player.getPosition()))
            .reduce((p1,p2) -> Percept.None.equals(p1) ? p2 : p1)
            .get(),
        gold.getPerceptFrom(player.getPosition())
    );
  }
}
