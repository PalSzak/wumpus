package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class WumpusWorldTest {
  WumpusWorld wumpusWorld;
  Gold gold;

  @BeforeEach
  public void setUp() {
    gold = new Gold(new Coord(0,0));
    wumpusWorld = new WumpusWorld(10, gold);
  }

  @Test
  public void playerCanMove() {
    Player player = new Player(new Coord(0, 0));
    Coord moveTo = player.move();

    wumpusWorld.move(player, moveTo);

    Assertions.assertEquals(moveTo, player.getPosition());
  }

  @Test
  public void arrowCanMove() {
    Arrow arrow = new Arrow(new Coord(0, 0), Direction.Directions.Up);
    Coord moveTo = arrow.move();
    wumpusWorld.move(arrow, moveTo);

    Assertions.assertEquals(moveTo, arrow.getPosition());
  }

  @Test
  public void canNotMoveOverTheBoard() {
    WumpusWorld smallWumpusWorld = new WumpusWorld(1, gold);
    Coord originalPosition = Coord.ZERO_ZERO;
    Player player = new Player(originalPosition);

    smallWumpusWorld.move(player, player.move());

    Assertions.assertEquals(originalPosition, player.getPosition());
  }

  @Test
  public void goldIsGlitteringInTheSameRoom() {
    Player player = new Player(Coord.ZERO_ZERO);
    List<Percept> percepts = wumpusWorld.getPerceptsOf(player);

    Assertions.assertTrue(percepts.contains(Percept.Glitter));
  }

}