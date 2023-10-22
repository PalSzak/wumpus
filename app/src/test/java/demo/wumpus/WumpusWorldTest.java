package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class WumpusWorldTest {
  Player player;
  WumpusWorld wumpusWorld;
  WumpusWorld smallWumpusWorld;
  Gold gold;
  Wumpus wumpi;
  List<Pit> pits;

  @BeforeEach
  public void setUp() {
    player = new Player(Room.START_POSITION);
    gold = new Gold(new Room(0,0));
    wumpi = new Wumpus(new Room(5,5));
    pits = List.of(
        new Pit(new Room(0,1)),
        new Pit(new Room(9,9))
    );
    wumpusWorld = new WumpusWorld(10, gold, wumpi, pits);
    smallWumpusWorld = new WumpusWorld(1, gold, wumpi, pits);
  }

  @Test
  public void playerCanMove() {
    Player player = new Player(new Room(0, 0));
    Room moveTo = player.move();

    wumpusWorld.move(player, moveTo);

    Assertions.assertEquals(moveTo, player.getPosition());
  }

  @Test
  public void arrowCanMove() {
    Arrow arrow = new Arrow(new Room(0, 0), Direction.Directions.Up);
    Room moveTo = arrow.move();
    wumpusWorld.move(arrow, moveTo);

    Assertions.assertEquals(moveTo, arrow.getPosition());
  }

  @Test
  public void canNotMoveOverTheBoard() {
    Room originalPosition = player.getPosition();


    smallWumpusWorld.move(player, player.move());
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
  public void goldIsGlittering() {
    Player player = new Player(Room.START_POSITION);
    List<Percept> percepts = wumpusWorld.getPerceptsOf(player);

    Assertions.assertTrue(percepts.contains(Percept.Glitter));
  }

  @Test
  public void wumpiIsStinky() {
    Player player = new Player(new Room(5,5));
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