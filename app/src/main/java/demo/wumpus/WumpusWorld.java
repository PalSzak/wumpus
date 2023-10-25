package demo.wumpus;

import java.util.ArrayList;
import java.util.List;

public class WumpusWorld {
  private static final int LOWER_COORD_BOUNDARY = -1;

  private final int upperCoordBoundary;
  private final Gold gold;
  private final Wumpus wumpus;
  private final List<Pit> pits;
  private final List<Actor> actors;


  public WumpusWorld(int size, Gold gold, Wumpus wumpus, List<Pit> pits) {
    this.actors = new ArrayList<>();
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

  public List<Percept> getPerceptsOf(Actor actor) {
    return List.of(
        wumpus.getPerceptFrom(actor.getPosition()),
        pits.stream()
            .map(pit -> pit.getPerceptFrom(actor.getPosition()))
            .reduce((p1,p2) -> Percept.None.equals(p1) ? p2 : p1)
            .orElseGet(() -> Percept.None),
        gold.getPerceptFrom(actor.getPosition()),
        actor.hadBump() ? Percept.Bump : Percept.None,
        wumpus.hadScream() ? Percept.Scream : Percept.None
    );
  }

  public void removeActor(Actor actor) {
    actors.remove(actor);
  }

  public List<Actor> getActors() {
    return actors;
  }

  public void addActor(Actor actor) {
    actors.add(actor);
  }
}
