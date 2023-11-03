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
  public static int GRID_SIZE = 3;

  private Player player;
  private List<Room> occupiedRooms;
  Random random;

  public GameFactory(Agent agent) {
    occupiedRooms = new LinkedList<>();
    player = new Player(Room.START_POSITION, agent);
    occupiedRooms.add(Room.START_POSITION);
    random = new Random();
  }

  public Game build() {
    return new GameImpl(
        player,
        new WumpusWorld(
            1,
            new Gold(getAFreeRoom()),
            new Wumpus(getAFreeRoom()),
            List.of(
                new Pit(getAFreeRoom()),
                new Pit(getAFreeRoom()),
                new Pit(getAFreeRoom())
            ))
    );
  }

  private Room getAFreeRoom() {
    Room aFreeRoom;

    do {
      aFreeRoom = Room.of(random.nextInt(GRID_SIZE), random.nextInt(GRID_SIZE));
    } while(occupiedRooms.contains(aFreeRoom));
    occupiedRooms.add(aFreeRoom);

    return aFreeRoom;
  }
}
