package demo.wumpus.internal.figures;

import demo.wumpus.api.Action;
import demo.wumpus.api.Agent;
import demo.wumpus.api.Percept;
import demo.wumpus.internal.Direction;
import demo.wumpus.internal.Room;
import demo.wumpus.internal.events.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Player implements Movable, Figure {
  private boolean hasArrow;
  private Room position;
  private final Agent agent;
  private final Direction faceDirection;
  private boolean bumpedToWall;

  public Player(Room startPosition) {
    this(startPosition, new Agent() {
      @Override
      public Action makeMove(List<Percept> percepts) {
        return Action.NO_OP;
      }
    });
  }

  public Player(Room startPosition, Agent agent) {
    position = startPosition;
    this.agent = agent;
    faceDirection = new Direction();
    hasArrow = true;
    bumpedToWall = false;
  }

  @Override
  public Room getDestination() {
    return position.getNeighbour(faceDirection.getCurrentDirection());
  }

  public void turnLeft() {
    faceDirection.turnLeft();
  }

  public void turnRight() {
    faceDirection.turnRight();
  }

  public Optional<Arrow> shoot() {
    if(hasArrow) {
      hasArrow = false;
      return Optional.of(new Arrow(position, faceDirection.getCurrentDirection()));
    } else {
      return Optional.empty();
    }
  }

  public void setPosition(Room position) {
    this.position = position;
  }

  @Override
  public void bumpedToWall() {
    bumpedToWall = true;
  }

  public Room getPosition() {
    return position;
  }

  public Percept hadBump() {
    return bumpedToWall ? Percept.Bump : Percept.None;
  }

  public void nextRound() {
    bumpedToWall = false;
  }

  @Override
  public List<GameAction> takeAction(List<Percept> percepts) {
    return switch(agent.makeMove(percepts)) {
      case GO_FORWARD -> List.of(new MoveAction(this));
      case TURN_LEFT -> List.of(new TurnLeft(this));
      case TURN_RIGHT -> List.of(new TurnRight(this));
      case GRAB -> List.of(new GrabTheGold(this));
      case SHOOT -> List.of(new Shoot(this));
      case CLIMB -> List.of(new ClimbOutFromDungeon(this));
      case NO_OP -> Collections.emptyList();
    };
  }
}
