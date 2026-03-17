package CoreGame;

public class Hunter extends Individual {
    public Hunter(Game game, int energy) {
        super(game, energy);
    }

    @Override
    public void chooseNextMove() {
        if (!this.isAlive()) {
            return;
        }

        Cell[] neighbors = this.getNeighboringCells();
        Gentleman gentleman = this.getGame().getGentleman();

        // Si le bonhomme est adjacent, le chasseur l'élimine.
        for (Cell cell : neighbors) {
            if (cell == gentleman.getPosition()) {
                this.getGame().stopGame(Game.HUNTER_CAUGHT_PLAYER);
                return;
            }
        }

        Cell bestCell = null;
        int bestDistance = Integer.MAX_VALUE;

        for (Cell cell : neighbors) {
            if (cell.someoneInside()) {
                continue;
            }

            int distance = this.getGame().getGrid().distanceToTarget(cell);
            if (distance < bestDistance) {
                bestDistance = distance;
                bestCell = cell;
            }
        }

        if (bestCell != null) {
            this.moveToCell(bestCell);
        }

        if (this.getPosition().isTarget()) {
            this.getGame().stopGame(Game.HUNTER_REACHED_TARGET);
        } else {
            this.getGame().checkEndBecauseBothDead();
        }
    }
}