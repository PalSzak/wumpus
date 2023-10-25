package demo.wumpus;

import demo.wumpus.events.GameAction;
import demo.wumpus.events.MoveAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
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
    this.addActor(new Arrow(new Room(0, 0), Direction.Directions.Up) {
      @Override
      public Optional<GameAction> takeAction(List<Percept> percepts) {
        arrowTakeAction.set(true);
        return Optional.empty();
      }
    });

    this.nextRound();

    Assertions.assertTrue(arrowTakeAction.get());
  }

  @Test
  public void arrowIsMovingForwardInEachRound() {
    Arrow arrow = new Arrow(new Room(0, 0), Direction.Directions.Up);
    this.addActor(arrow);

    this.nextRound();

    Assertions.assertEquals(new Room(1,0), arrow.getPosition());
  }

  @Test
  public void playerGetsTheirPerceptsInEachRound() {
    AtomicBoolean playerGotPercepts = new AtomicBoolean(false);
    replacePlayerWith(new Player(new Room(0, 0)) {
      public Optional<GameAction> takeAction(List<Percept> percepts) {
        playerGotPercepts.set(true);
        return Optional.empty();
      }
    });

    this.nextRound();

    Assertions.assertTrue(playerGotPercepts.get());
  }

  @Test
  public void playerCanMoveForward() {
    Player player = new Player(new Room(0, 0)) {
      public Optional<GameAction> takeAction(List<Percept> percepts) {
        return Optional.of(new MoveAction(this));
      }
    };
    replacePlayerWith(player);

    this.nextRound();

    Assertions.assertEquals(new Room(1,0), player.getPosition());
  }

  private void replacePlayerWith(Player player) {
    actors.removeIf(a -> a instanceof Player);
    actors.add(player);
  }

}
