package demo.wumpus;

public class WumpusWorld {

  public void move(Player player, Coord moveTo) {
    player.setPosition(moveTo);
  }


  public void move(Arrow arrow, Coord moveTo) {
    arrow.setPosition(moveTo);
  }
}
