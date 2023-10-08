package demo.wumpus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WumpusWorldTest {

  @Test
  public void playerCanMove() {
    Player player = new Player(new Coord(0, 0));
    WumpusWorld wumpusWorld = new WumpusWorld();
    Coord moveTo = player.move();
  
    wumpusWorld.move(player, moveTo);

    Assertions.assertEquals(moveTo, player.getPosition());
  }
}