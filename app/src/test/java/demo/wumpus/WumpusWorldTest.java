package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WumpusWorldTest {
  WumpusWorld wumpusWorld;

  @BeforeEach
  public void setUp() {
    wumpusWorld = new WumpusWorld(10);
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
    WumpusWorld smallWumpusWorld = new WumpusWorld(1);
    Coord originalPosition = new Coord(0, 0);
    Player player = new Player(originalPosition);

    smallWumpusWorld.move(player, player.move());

    Assertions.assertEquals(originalPosition, player.getPosition());
  }
}