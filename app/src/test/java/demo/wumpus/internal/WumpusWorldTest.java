package demo.wumpus.internal;

import demo.wumpus.api.Percept;
import demo.wumpus.internal.*;
import demo.wumpus.internal.figures.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class WumpusWorldTest {
  Player player;
  WumpusWorld wumpusWorld;
  WumpusWorld smallWumpusWorld;
  Gold gold;
  Wumpus wumpus;
  List<Pit> pits;

  @BeforeEach
  public void setUp() {
    player = new Player(Room.START_POSITION);
    gold = new Gold(Room.START_POSITION);
    wumpus = new Wumpus(Room.of(5,5));
    pits = List.of(
        new Pit(Room.of(0,1)),
        new Pit(Room.of(9,9))
    );
    wumpusWorld = new WumpusWorld(10, gold, wumpus, pits);
    smallWumpusWorld = new WumpusWorld(1, gold, wumpus, pits);
  }

  @Test
  public void playerCanMove() {
    Player player = new Player(Room.START_POSITION);
    Room moveTo = player.getDestination();

    wumpusWorld.move(player, moveTo);

    Assertions.assertEquals(moveTo, player.getPosition());
  }

  @Test
  public void arrowCanMove() {
    Arrow arrow = new Arrow(Room.START_POSITION, Direction.Directions.Up);
    Room moveTo = arrow.getDestination();
    wumpusWorld.move(arrow, moveTo);

    Assertions.assertEquals(moveTo, arrow.getPosition());
  }

  @Test
  public void canNotMoveOverTheBoard() {
    Room originalPosition = player.getPosition();

    smallWumpusWorld.move(player, player.getDestination());
    List<Percept> percepts = smallWumpusWorld.getPerceptsOf(player);

    Assertions.assertEquals(originalPosition, player.getPosition());
    Assertions.assertTrue(percepts.contains(Percept.Bump),"Bump");
  }

  @Test
  public void bumpIsNotPermanent() {
    canNotMoveOverTheBoard();
    player.nextRound();
    List<Percept> percepts = smallWumpusWorld.getPerceptsOf(player);
    Assertions.assertFalse(percepts.contains(Percept.Bump));
  }

  @Test
  public void wumpusScreamsWhenDie() {
    wumpus.die();
    List<Percept> percepts = wumpusWorld.getPerceptsOf(player);

    Assertions.assertTrue(percepts.contains(Percept.Scream), "When a wumpus is killed, it gives a woeful scream");
  }

  @Test
  public void wumpusScreamIsNotPermanent() {
    wumpusScreamsWhenDie();
    wumpus.nextRound();
    List<Percept> percepts = wumpusWorld.getPerceptsOf(player);

    Assertions.assertFalse(percepts.contains(Percept.Scream));
  }

  @Test
  public void goldIsGlittering() {
    Player player = new Player(Room.START_POSITION);
    List<Percept> percepts = wumpusWorld.getPerceptsOf(player);

    Assertions.assertTrue(percepts.contains(Percept.Glitter));
  }

  @Test
  public void wumpusIsStinky() {
    Player player = new Player(Room.of(5,5));
    List<Percept> percepts = wumpusWorld.getPerceptsOf(player);

    Assertions.assertTrue(percepts.contains(Percept.Stench));
  }

  @Test
  public void pitIsWindy() {
    Player player = new Player(Room.START_POSITION);
    List<Percept> percepts = wumpusWorld.getPerceptsOf(player);

    Assertions.assertTrue(percepts.contains(Percept.Breeze));
  }



}