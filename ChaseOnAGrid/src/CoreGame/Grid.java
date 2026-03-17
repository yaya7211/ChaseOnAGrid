package CoreGame;

import AdaptorsForNotToChange.AdaptorFire;
import AdaptorsForNotToChange.AdaptorFruit;
import notToChange.Fire;
import notToChange.Fruit;
import notToChange.ItemInterface;

import java.util.concurrent.ThreadLocalRandom;

public class Grid {
    private final int width;
    private final int height;
    private final Cell[][] cells;
    private Cell targetCell;

    public Grid(int x, int y) {
        this.width = x;
        this.height = y;
        this.cells = new Cell[x][y];

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                this.cells[i][j] = new Cell(i, j);
            }
        }

        this.placeTarget();
        this.placeRandomItems();
    }

    private void placeTarget() {
        int x = ThreadLocalRandom.current().nextInt(0, this.width);
        int y = ThreadLocalRandom.current().nextInt(0, this.height);

        this.targetCell = this.cells[x][y];
        this.targetCell.setTarget(true);
    }

    private void placeRandomItems() {
        int nbItems = Math.max(1, (this.width * this.height) / 5);

        for (int i = 0; i < nbItems; i++) {
            Cell cell = getRandomCellWithoutItem();
            cell.setItem(createRandomItem());
        }
    }

    private ItemInterface createRandomItem() {
        int choice = ThreadLocalRandom.current().nextInt(4);

        switch (choice) {
            case 0:
                return new Item(ThreadLocalRandom.current().nextInt(1, 4));   // bonus classique
            case 1:
                return new Item(-ThreadLocalRandom.current().nextInt(1, 4));  // malus classique
            case 2:
                return new AdaptorFire(new Fire());
            default:
                return new AdaptorFruit(new Fruit());
        }
    }

    private Cell getRandomCellWithoutItem() {
        Cell cell;
        do {
            int x = ThreadLocalRandom.current().nextInt(0, this.width);
            int y = ThreadLocalRandom.current().nextInt(0, this.height);
            cell = this.cells[x][y];
        } while (cell.getItem() != null);
        return cell;
    }

    public Cell[] getNeighboringCells(Cell position) {
        int x = position.getX();
        int y = position.getY();

        Cell[] neighbors = new Cell[8];
        int count = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue;
                }

                int nx = x + dx;
                int ny = y + dy;

                if (nx >= 0 && nx < this.width && ny >= 0 && ny < this.height) {
                    neighbors[count++] = this.getCell(nx, ny);
                }
            }
        }

        Cell[] result = new Cell[count];
        for (int i = 0; i < count; i++) {
            result[i] = neighbors[i];
        }

        return result;
    }

    public Cell getCell(int x, int y) {
        return this.cells[x][y];
    }

    public Cell getRandomFreeNonTargetCell() {
        Cell cell;
        do {
            int x = ThreadLocalRandom.current().nextInt(0, this.width);
            int y = ThreadLocalRandom.current().nextInt(0, this.height);
            cell = this.cells[x][y];
        } while (cell.someoneInside() || cell.isTarget());

        return cell;
    }

    public int distanceToTarget(Cell cell) {
        return Math.abs(cell.getX() - this.targetCell.getX())
                + Math.abs(cell.getY() - this.targetCell.getY());
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}