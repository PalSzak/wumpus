package demo.wumpus.api;

import demo.wumpus.internal.GameImpl;
import demo.wumpus.internal.figures.Gold;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.figures.Wumpus;

import java.util.Collections;

public class GameFactory {
  private Player player;

  public GameFactory() {
    player = new Player(Room.START_POSITION);
  }

  public Game build() {
    return new GameImpl(player, new WumpusWorld(1,new Gold(Room.START_POSITION),new Wumpus(Room.START_POSITION), Collections.emptyList()));
  }
}
