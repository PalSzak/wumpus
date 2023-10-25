package demo.wumpus;

public class GameFactory {
  private Player player;

  public GameFactory() {
    player = new Player(new Room(0, 0));
  }

  public Game build() {
    return new Game(player, new WumpusWorld(1,null,null,null));
  }
}
