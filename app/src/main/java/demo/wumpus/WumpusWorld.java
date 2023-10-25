package demo.wumpus;

import java.util.ArrayList;
import java.util.List;

public class WumpusWorld {
  private static final int LOWER_COORD_BOUNDARY = -1;

  private final int upperCoordBoundary;
  private final Gold gold;
  private final Wumpus wumpus;
  private final List<Pit> pits;
  private final List<Figure> figures;


  public WumpusWorld(int size, Gold gold, Wumpus wumpus, List<Pit> pits) {
    this.figures = new ArrayList<>();
    this.upperCoordBoundary = size;
    this.gold = gold;
    this.wumpus = wumpus;
    this.pits = pits;
  }

  public void move(Movable movable, Room moveTo) {
    if(LOWER_COORD_BOUNDARY < moveTo.getX() && moveTo.getX() < upperCoordBoundary &&
        LOWER_COORD_BOUNDARY < moveTo.getY() && moveTo.getY() < upperCoordBoundary) {
      movable.setPosition(moveTo);
    } else {
      movable.bumpedToWall();
    }
  }

  public List<Percept> getPerceptsOf(Figure figure) {
    return List.of(
        wumpus.getPerceptFrom(figure.getPosition()),
        pits.stream()
            .map(pit -> pit.getPerceptFrom(figure.getPosition()))
            .reduce((p1,p2) -> Percept.None.equals(p1) ? p2 : p1)
            .orElseGet(() -> Percept.None),
        gold.getPerceptFrom(figure.getPosition()),
        figure instanceof Movable ? ((Movable) figure).hadBump() : Percept.None,
        wumpus.hadScream() ? Percept.Scream : Percept.None
    );
  }

  public void removeFigure(Figure figure) {
    figures.remove(figure);
  }

  public List<Figure> getFigures() {
    return figures;
  }

  public void addFigure(Figure figure) {
    figures.add(figure);
  }
}
