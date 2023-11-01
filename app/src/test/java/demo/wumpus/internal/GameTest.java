package demo.wumpus.internal;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.events.ClimbOutFromDungeon;
import demo.wumpus.internal.events.GameAction;
import demo.wumpus.internal.events.GrabTheGold;
import demo.wumpus.internal.events.MoveAction;
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
  private static final Room GOLD_POSITION = Room.of(2,2);
  private static final Room WUMPUS_POSITION = Room.of(5,5);
  private static final Room PIT_POSITION = Room.of(3,3);

  public GameTest() {
    super(
        new Player(Room.START_POSITION),
        new WumpusWorld(
            10,
            new Gold(GOLD_POSITION),
            new Wumpus(WUMPUS_POSITION),
            List.of(new Pit(PIT_POSITION)))
    );
  }

  @Test
  public void arrowIsMovingForwardInEachRound() {
    Arrow arrow = new Arrow(Room.START_POSITION, Direction.Directions.Up);
    this.addFigure(arrow);

    this.nextRound();

    Assertions.assertEquals(Room.of(1,0), arrow.getPosition());
  }

  @Test
  public void arrowHasBeenRemovedWhenItBumpedToWall() {
    AtomicInteger callCount = new AtomicInteger(0);
    this.addFigure(new Arrow(Room.of(9,0), Direction.Directions.Up){
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
    Arrow arrow = new Arrow(Room.of(4, 5), Direction.Directions.Up);
    this.addFigure(arrow);

    this.nextRound();

    Assertions.assertFalse(getWumpus().isAlive(), "Arrow should kill the wumpus");
  }

  @Test
  public void arrowHasBeenRemovedWhenItHitsTheWumpus() {
    AtomicInteger callCount = new AtomicInteger(0);
    this.addFigure(new Arrow(Room.of(4, 5), Direction.Directions.Up){
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
    replacePlayerWith(new Player(Room.START_POSITION) {
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
    Player player = new Player(Room.START_POSITION) {
      public List<GameAction> takeAction(List<Percept> percepts) {
        return List.of(new MoveAction(this));
      }
    };
    replacePlayerWith(player);

    this.nextRound();

    Assertions.assertEquals(Room.of(1,0), player.getPosition());
  }

  @Test
  public void wumpusEatsPlayer() {
    replacePlayerWith(new Player(WUMPUS_POSITION));

    this.nextRound();

    Assertions.assertNull(getPlayer());
  }

  @Test
  public void deadWumpusWontEatPlayer() {
    replacePlayerWith(new Player(WUMPUS_POSITION));
    getWumpus().die();

    this.nextRound();

    Assertions.assertNotNull(getPlayer());
  }

  @Test
  public void gameEndsWhenNoPlayerInDungeon() throws InterruptedException {
    replacePlayerWith(new Player(WUMPUS_POSITION));
    ExecutorService executorService = Executors.newFixedThreadPool(1);

    executorService.execute(this);

    executorService.shutdown();
    boolean terminated = executorService.awaitTermination(1, TimeUnit.SECONDS);

    Assertions.assertTrue(terminated, "Player should die miserable dead.");
  }

  @Test
  public void pitKillsPlayer() {
    replacePlayerWith(new Player(PIT_POSITION));

    this.nextRound();

    Assertions.assertNull(getPlayer());
  }

  @Test
  public void playerCanClimbOutFromStartPosition() {
    replacePlayerWith(new Player(Room.START_POSITION){
      @Override
      public List<GameAction> takeAction(List<Percept> percepts) {
        return List.of(new ClimbOutFromDungeon(this));
      }
    });

    this.nextRound();

    Assertions.assertNull(getPlayer(), "Player should be able to climb out.");
  }

  @Test
  public void playerCanNotClimbOutFromOtherPositions() {
    replacePlayerWith(new Player(Room.of(0,1)){
      @Override
      public List<GameAction> takeAction(List<Percept> percepts) {
        return List.of(new ClimbOutFromDungeon(this));
      }
    });

    this.nextRound();

    Assertions.assertNotNull(getPlayer(), "Player shouldn't be able to climb out.");
  }

  @Test
  public void playerCanGrabGold() {
    replacePlayerWith(new Player(GOLD_POSITION){
      @Override
      public List<GameAction> takeAction(List<Percept> percepts) {
        return List.of(new GrabTheGold(this));
      }
    });

    this.nextRound();

    Assertions.assertNull(getGold(), "Gold should remove when grabbed");
  }

  @Test
  public void playerCanNotGrabGoldFromOtherPosition() {
    replacePlayerWith(new Player(Room.START_POSITION){
      @Override
      public List<GameAction> takeAction(List<Percept> percepts) {
        return List.of(new GrabTheGold(this));
      }
    });

    this.nextRound();

    Assertions.assertNotNull(getGold(), "Gold is grabbable only from same cell");
  }

  private Gold getGold() {
    return getWorld().getFigures(Gold.class).findFirst().orElseGet( () -> null);
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
