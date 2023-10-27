package demo.wumpus.api;

import demo.wumpus.internal.GameImpl;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;

public class GameFactory {
  private Player player;

  public GameFactory() {
    player = new Player(new Room(0, 0));
  }

  public Game build() {
    return new GameImpl(player, new WumpusWorld(1,null,null,null));
  }
}
