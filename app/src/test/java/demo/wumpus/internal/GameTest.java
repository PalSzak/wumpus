package demo.wumpus.internal;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.events.GameAction;
import demo.wumpus.internal.events.MoveAction;
import demo.wumpus.internal.*;
import demo.wumpus.internal.events.RemoveFigure;
import demo.wumpus.internal.figures.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class GameTest extends GameImpl {

  public GameTest() {
    super(
        new Player(new Room(0, 0)),
        new WumpusWorld(
            10,
            new Gold(new Room(2, 2)),
            new Wumpus(new Room(5, 5)),
            List.of(new Pit(new Room(2,2))))
    );
  }

  @Test
  public void arrowIsMovingForwardInEachRound() {
    Arrow arrow = new Arrow(new Room(0, 0), Direction.Directions.Up);
    this.addFigure(arrow);

    this.nextRound();

    Assertions.assertEquals(new Room(1,0), arrow.getPosition());
  }

  @Test
  public void arrowHasBeenRemovedWhenItBumpedToWall() {
    AtomicInteger callCount = new AtomicInteger(0);
    this.addFigure(new Arrow(new Room(9,0), Direction.Directions.Up){
      @Override
      public List<GameAction> takeAction(List<Percept> percepts) {
        callCount.incrementAndGet();
        return super.takeAction(percepts);
      }
    });
    this.nextRound();
    this.nextRound();

    this.nextRound();

    Assertions.assertEquals(2, callCount.get(), "Arrow should be dismissed after bumped to wall");
  }

  @Test
  public void arrowKillTheWumpusInSameCell() {
    Arrow arrow = new Arrow(new Room(4, 5), Direction.Directions.Up);
    this.addFigure(arrow);

    this.nextRound();

    Assertions.assertFalse(getWumpus().isAlive(), "Arrow should kill the wumpus");
  }

  @Test
  public void arrowHasBeenRemovedWhenItHitsTheWumpus() {
    AtomicInteger callCount = new AtomicInteger(0);
    this.addFigure(new Arrow(new Room(4, 5), Direction.Directions.Up){
      @Override
      public List<GameAction> takeAction(List<Percept> percepts) {
        callCount.incrementAndGet();
        return super.takeAction(percepts);
      }
    });
    this.nextRound();

    this.nextRound();
    Assertions.assertEquals(1, callCount.get(), "Arrow should be dismissed after bumped to wall");
  }

  @Test
  public void playerGetsTheirPerceptsInEachRound() {
    AtomicBoolean playerGotPercepts = new AtomicBoolean(false);
    replacePlayerWith(new Player(new Room(0, 0)) {
      public List<GameAction> takeAction(List<Percept> percepts) {
        playerGotPercepts.set(true);
        return super.takeAction(percepts);
      }
    });

    this.nextRound();

    Assertions.assertTrue(playerGotPercepts.get());
  }

  @Test
  public void playerCanMoveForward() {
    Player player = new Player(new Room(0, 0)) {
      public List<GameAction> takeAction(List<Percept> percepts) {
        return List.of(new MoveAction(this));
      }
    };
    replacePlayerWith(player);

    this.nextRound();

    Assertions.assertEquals(new Room(1,0), player.getPosition());
  }

  @Test
  public void wumpusEatsPlayer() {
    replacePlayerWith(new Player(new Room(5,5)));

    this.nextRound();

    Assertions.assertNull(getPlayer());
  }

  @Test
  public void gameEndsWhenNoPlayerInDungeon() throws InterruptedException {
    replacePlayerWith(new Player(new Room(5,5)));
    ExecutorService executorService = Executors.newFixedThreadPool(1);

    executorService.execute(this);

    executorService.shutdown();
    boolean terminated = executorService.awaitTermination(1, TimeUnit.SECONDS);

    Assertions.assertTrue(terminated, "Player should die miserable dead.");
  }

  @Test
  public void pitKillsPlayer() {
    replacePlayerWith(new Player(new Room(2,2)));

    this.nextRound();

    Assertions.assertNull(getPlayer());
  }

  private Player getPlayer() {
    return getWorld().getFigures(Player.class).findFirst().orElseGet( () -> null);
  }

  private void replacePlayerWith(Player player) {
    getWorld().removeFigure(getPlayer());
    getWorld().addFigure(player);
  }

  private Wumpus getWumpus() {
    return (Wumpus) getWorld().getFigures(Wumpus.class).findFirst().get();
  }

}
