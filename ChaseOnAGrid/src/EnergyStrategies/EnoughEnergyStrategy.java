package EnergyStrategies;

import java.util.List;
import CoreGame.Cell;

public class EnoughEnergyStrategy implements MovingStrategy {
    @Override
    public Cell[] filterReachableCells(Cell[] cells, List<Cell> explorationHistory) {
        return cells;
    }
}