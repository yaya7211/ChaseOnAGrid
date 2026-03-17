package CoreGame;

import java.util.Scanner;

public class Interface {
    private final Game game;
    private final Scanner scanner;

    public Interface(Game game) {
        this.game = game;
        this.scanner = new Scanner(System.in);
    }

    public void displayAroundPlayer(Cell[] reachableCells) {
        Grid grid = this.game.getGrid();
        Cell playerCell = this.game.getGentleman().getPosition();

        int px = playerCell.getX();
        int py = playerCell.getY();

        int minX = Math.max(0, px - 1);
        int maxX = Math.min(grid.getWidth() - 1, px + 1);
        int minY = Math.max(0, py - 1);
        int maxY = Math.min(grid.getHeight() - 1, py + 1);

        int cols = maxX - minX + 1;

        printTopBorder(cols);

        for (int y = minY; y <= maxY; y++) {
            System.out.print("│");
            for (int x = minX; x <= maxX; x++) {
                Cell cell = grid.getCell(x, y);
                String content;

                if (x == px && y == py) {
                    content = "o";
                } else if (!containsCell(reachableCells, cell)) {
                    content = "?";
                } else {
                    content = cell.getDisplayForPlayer();
                }

                System.out.printf("%-3s│", " " + content);
            }
            System.out.println();
        }

        printBottomBorder(cols);
    }

    public Cell askPlayerMove(Gentleman gentleman, Cell[] reachableCells) {
        Cell current = gentleman.getPosition();
        int x = current.getX();
        int y = current.getY();

        displayAroundPlayer(reachableCells);
        System.out.println("Déplacements possibles : h, hd, hg, d, g, b, bd, bg");

        while (true) {
            System.out.print("Choix : ");
            String choice = this.scanner.next().toLowerCase();

            int dx = 0;
            int dy = 0;

            switch (choice) {
                case "h":
                    dy = -1;
                    break;
                case "b":
                    dy = 1;
                    break;
                case "g":
                    dx = -1;
                    break;
                case "d":
                    dx = 1;
                    break;
                case "hg":
                    dx = -1;
                    dy = -1;
                    break;
                case "hd":
                    dx = 1;
                    dy = -1;
                    break;
                case "bg":
                    dx = -1;
                    dy = 1;
                    break;
                case "bd":
                    dx = 1;
                    dy = 1;
                    break;
                default:
                    System.out.println("Commande invalide.");
                    continue;
            }

            int nx = x + dx;
            int ny = y + dy;

            if (nx < 0 || nx >= this.game.getGrid().getWidth()
                    || ny < 0 || ny >= this.game.getGrid().getHeight()) {
                System.out.println("Déplacement impossible : bord de la grille.");
                continue;
            }

            Cell selectedCell = this.game.getGrid().getCell(nx, ny);

            if (!containsCell(reachableCells, selectedCell)) {
                System.out.println("Cette case n'est pas atteignable.");
                continue;
            }

            return selectedCell;
        }
    }

    private boolean containsCell(Cell[] cells, Cell target) {
        for (Cell cell : cells) {
            if (cell == target) {
                return true;
            }
        }
        return false;
    }

    private void printTopBorder(int cols) {
        System.out.print("┌");
        for (int i = 0; i < cols; i++) {
            System.out.print("───");
            if (i < cols - 1) {
                System.out.print("┬");
            }
        }
        System.out.println("┐");
    }

    private void printBottomBorder(int cols) {
        System.out.print("└");
        for (int i = 0; i < cols; i++) {
            System.out.print("───");
            if (i < cols - 1) {
                System.out.print("┴");
            }
        }
        System.out.println("┘");
    }
}