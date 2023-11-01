package demo.wumpus.api;

import demo.wumpus.internal.GameImpl;
import demo.wumpus.internal.figures.Gold;
import demo.wumpus.internal.figures.Pit;
import demo.wumpus.internal.figures.Player;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.WumpusWorld;
import demo.wumpus.internal.figures.Wumpus;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GameFactory {
  public static final int WUMPUS_COUNT = 1;
  public static int PIT_COUNT = 3;
  private Player player;
  private List<Room> occupiedRooms;


  public GameFactory() {
    occupiedRooms = new LinkedList<>();
    player = new Player(Room.START_POSITION);
    occupiedRooms.add(Room.START_POSITION);

  }

  public Game build() {
    return new GameImpl(
        player,
        new WumpusWorld(
            1,
            new Gold(Room.START_POSITION),
            new Wumpus(Room.START_POSITION),
            List.of(
                new Pit(Room.START_POSITION),
                new Pit(Room.START_POSITION),
                new Pit(Room.START_POSITION)
            ))
    );
  }
}
