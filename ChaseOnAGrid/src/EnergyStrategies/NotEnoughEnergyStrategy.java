package EnergyStrategies;

import java.util.ArrayList;
import java.util.List;
import CoreGame.Cell;

public class NotEnoughEnergyStrategy implements MovingStrategy {
    @Override
    public Cell[] filterReachableCells(Cell[] cells, List<Cell> explorationHistory) {
        List<Cell> reachable = new ArrayList<Cell>();

        for (Cell cell : cells) {
            if (!explorationHistory.contains(cell)) {
                reachable.add(cell);
            }
        }

        return reachable.toArray(new Cell[reachable.size()]);
    }
}