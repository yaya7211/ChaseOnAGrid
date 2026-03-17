package CoreGame;

public abstract class Individual {
    private final Game game;
    private int energy;
    private Cell position;
    private boolean alive;

    public Individual(Game game, int energy) {
        this.game = game;
        this.energy = energy;
        this.alive = true;

        Cell startCell = this.game.getGrid().getRandomFreeNonTargetCell();
        startCell.putInside(this);
    }

    public abstract void chooseNextMove();

    public void moveToCell(Cell destination) {
        if (destination == null || !this.alive) {
            return;
        }

        if (this.position != null) {
            this.position.removeIndividual();
        }

        destination.putInside(this);

        // Coût du déplacement
        this.energy -= 1;

        // Effet éventuel de l'élément
        destination.applyItemEffect(this);

        if (this.energy <= 0) {
            this.energy = 0;
            this.alive = false;
        }
    }

    public Cell[] getNeighboringCells() {
        return this.game.getGrid().getNeighboringCells(this.position);
    }

    public void addEnergy(int delta) {
        this.energy += delta;
        if (this.energy <= 0) {
            this.energy = 0;
            this.alive = false;
        }
    }

    public Game getGame() {
        return this.game;
    }

    public int getEnergy() {
        return this.energy;
    }

    public Cell getPosition() {
        return this.position;
    }

    public void setPosition(Cell position) {
        this.position = position;
    }

    public boolean isAlive() {
        return this.alive;
    }

}