package demo.wumpus.api;

import java.util.List;

public interface Agent {

  Action makeMove(List<Percept> percepts);
}
