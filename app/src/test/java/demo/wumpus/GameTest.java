package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameTest extends Game {

  public GameTest() {
    super(new Player(new Room(0, 0)));
  }

  @Test
  public void arrowHasBeenCalledToMakeItsMoveInEachRound() {
    AtomicBoolean arrowTakeAction = new AtomicBoolean(false);

    this.setArrow(new Arrow(new Room(0, 0), Direction.Directions.Up) {
      @Override
      public void takeAction() {
        arrowTakeAction.set(true);
      }
    });

    this.nextRound();
    Assertions.assertTrue(arrowTakeAction.get());
  }
}
