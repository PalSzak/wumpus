package demo.wumpus.internal;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.figures.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WumpusWorld {
  private static final int LOWER_COORDINATE_BOUNDARY = -1;

  private final int upperCoordinateBoundary;
  private final Wumpus wumpus;
  private final List<Pit> pits;
  private final List<Figure> figures;


  public WumpusWorld(int size, Gold gold, Wumpus wumpus, List<Pit> pits) {
    this.figures = new ArrayList<>();
    this.upperCoordinateBoundary = size;
    this.wumpus = wumpus;
    this.pits = pits;

    figures.add(wumpus);
    figures.add(gold);
    figures.addAll(pits);
  }

  public void move(Movable movable, Room moveTo) {
    if(LOWER_COORDINATE_BOUNDARY < moveTo.getX() && moveTo.getX() < upperCoordinateBoundary &&
        LOWER_COORDINATE_BOUNDARY < moveTo.getY() && moveTo.getY() < upperCoordinateBoundary) {
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
        getFigures(Gold.class).findFirst().map(g -> g.getPerceptFrom(figure.getPosition())).orElseGet(() -> Percept.None),
        figure instanceof Movable ? ((Movable) figure).hadBump() : Percept.None,
        wumpus.hadScream()
    );
  }

  public void removeFigure(Figure figure) {
    figures.remove(figure);
  }

  public Stream<Figure> getFigures() {
    return getFigures(Figure.class);
  }

  public <T extends Figure> Stream<T> getFigures(Class<T> figureType) {
    return figures.stream()
        .filter(f -> figureType.isAssignableFrom(f.getClass()))
        .map( f -> (T) f);
  }

  public void addFigure(Figure figure) {
    figures.add(figure);
  }

}
