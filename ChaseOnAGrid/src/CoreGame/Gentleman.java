package CoreGame;

import EnergyStrategies.EnoughEnergyStrategy;
import EnergyStrategies.MovingStrategy;
import EnergyStrategies.NotEnoughEnergyStrategy;

import java.util.ArrayList;
import java.util.List;

public class Gentleman extends Individual {
    private final List<Cell> explorationHistory = new ArrayList<Cell>();

    public Gentleman(Game game, int energy) {
        super(game, energy);
        this.explorationHistory.add(this.getPosition());
    }

    @Override
    public void chooseNextMove() {
        if (!this.isAlive()) {
            return;
        }

        Cell[] cells = this.getNeighboringCells();

        MovingStrategy strategy;
        if (this.getEnergy() > this.getGame().getHighValue()) {
            strategy = new EnoughEnergyStrategy();
        } else {
            strategy = new NotEnoughEnergyStrategy();
        }

        Cell[] reachableCells = strategy.filterReachableCells(cells, this.getExplorationHistory());

        if (reachableCells.length == 0) {
            this.getGame().stopGame(Game.GENTLEMAN_STUCK_NO_ENERGY);
            return;
        }

        Cell destination = this.getGame().getUi().askPlayerMove(this, reachableCells);

        this.moveToCell(destination);
        this.explorationHistory.add(this.getPosition());

        if (this.getPosition().isTarget()) {
            this.getGame().stopGame(Game.PLAYER_REACHED_TARGET);
        } else {
            this.getGame().checkEndBecauseBothDead();
        }
    }

    public List<Cell> getExplorationHistory() {
        return this.explorationHistory;
    }
}