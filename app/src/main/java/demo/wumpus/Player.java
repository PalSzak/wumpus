package demo.wumpus;

public class Player {

  public ImmputableCoord getPosition() {
    return new ImmputableCoord(0,0);
  }

  public ImmputableCoord move() {
    return new ImmputableCoord(1, 0);
  }
}
