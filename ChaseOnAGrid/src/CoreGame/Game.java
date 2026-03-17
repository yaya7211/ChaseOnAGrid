package CoreGame;

public class Game {
    public static final int PLAYER_REACHED_TARGET = -1;
    public static final int BOTH_EXHAUSTED = 0;
    public static final int HUNTER_REACHED_TARGET = 1;
    public static final int HUNTER_CAUGHT_PLAYER = 2;
    public static final int GENTLEMAN_STUCK_NO_ENERGY = -2;

    public static final int highValue = 4;

    private static final int[] GRID_SIZE = {10, 10};

    private Grid grid;
    private Gentleman gentleman;
    private Hunter hunter;
    private Interface ui;
    private boolean onGoing;

    public Game() {
        this.startGame();
    }

    private void startGame() {
        this.grid = new Grid(GRID_SIZE[0], GRID_SIZE[1]);
        this.gentleman = new Gentleman(this, 10);
        this.hunter = new Hunter(this, 10);
        this.ui = new Interface(this);
        this.onGoing = true;
    }

    public void play() {
        while (this.onGoing) {
            if (this.gentleman.isAlive()) {
                this.gentleman.chooseNextMove();
            }
            if (!this.onGoing) {
                break;
            }

            if (this.hunter.isAlive()) {
                this.hunter.chooseNextMove();
            }
            if (!this.onGoing) {
                break;
            }

            checkEndBecauseBothDead();
        }
    }

    public void checkEndBecauseBothDead() {
        if (!this.gentleman.isAlive() && !this.hunter.isAlive()) {
            stopGame(BOTH_EXHAUSTED);
        }
    }

    public void stopGame(int reason) {
        this.onGoing = false;

        System.out.println();

        switch (reason) {
            case PLAYER_REACHED_TARGET:
                System.out.println("Le bonhomme a atteint la cible. Victoire du bonhomme.");
                break;
            case BOTH_EXHAUSTED:
                System.out.println("Les deux joueurs n'ont plus d'énergie. Fin de partie.");
                break;
            case HUNTER_REACHED_TARGET:
                System.out.println("Le chasseur a atteint la cible. Victoire du chasseur.");
                break;
            case HUNTER_CAUGHT_PLAYER:
                System.out.println("Le chasseur a éliminé le bonhomme. Victoire du chasseur.");
                break;
            case GENTLEMAN_STUCK_NO_ENERGY:
                System.out.println("Le bonhomme ne peut plus atteindre aucune case. Défaite par manque d'énergie.");
                break;
            default:
                System.out.println("Fin de partie.");
        }
    }

    public Grid getGrid() {
        return this.grid;
    }

    public Gentleman getGentleman() {
        return this.gentleman;
    }

    public Interface getUi() {
        return this.ui;
    }

    public int getHighValue() {
        return highValue;
    }

}