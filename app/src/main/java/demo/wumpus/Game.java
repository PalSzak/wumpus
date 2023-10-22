package demo.wumpus;

public class Game {
  private Player player;

  public Game() {
    player = new Player(new Room(0, 0));
  }

  public Player getPlayer() {
    return this.player;
  }
}
