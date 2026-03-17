package EnergyStrategies;

import CoreGame.Cell;
import java.util.List;


public interface MovingStrategy {
    Cell[] filterReachableCells(Cell[] cells, List<Cell> explorationHistory);
}