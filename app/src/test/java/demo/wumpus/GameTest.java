package demo.wumpus;

import demo.wumpus.events.GameAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameTest extends Game {

  public GameTest() {
    super(
        new Player(new Room(0, 0)),
        new WumpusWorld(10, new Gold(new Room(2, 2)), new Wumpus(new Room(5, 5)), Collections.emptyList())
    );
  }

  @Test
  public void arrowHasBeenCalledToMakeItsMoveInEachRound() {
    AtomicBoolean arrowTakeAction = new AtomicBoolean(false);

    this.setArrow(new Arrow(new Room(0, 0), Direction.Directions.Up) {
      @Override
      public GameAction takeAction() {
        arrowTakeAction.set(true);
        return new GameAction() {
          @Override
          public void run(WumpusWorld world) {

          }
        };
      }
    });

    this.nextRound();
    Assertions.assertTrue(arrowTakeAction.get());
  }

  @Test
  public void arrowIsMovingForwardInEachRound() {
    Arrow arrow = new Arrow(new Room(0, 0), Direction.Directions.Up);
    this.setArrow(arrow);
    this.nextRound();
    Assertions.assertEquals(new Room(1,0), arrow.getPosition());
  }
}
