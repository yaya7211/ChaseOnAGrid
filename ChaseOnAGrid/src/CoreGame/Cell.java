package CoreGame;

import notToChange.ItemInterface;

public class Cell {

    private final int x;
    private final int y;
    private Individual individual;
    private ItemInterface item;
    private boolean target;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.individual = null;
        this.item = null;
        this.target = false;
    }

    public void putInside(Individual individual) {
        this.individual = individual;
        individual.setPosition(this);
    }

    public void removeIndividual() {
        this.individual = null;
    }

    public boolean someoneInside() {
        return this.individual != null;
    }

    public void applyItemEffect(Individual individual) {
        if (this.item == null) {
            return;
        }

        individual.addEnergy(this.item.getEnergie());

        // Si c'est un bonus, il disparaît.
        if (this.item.getEnergie() > 0) {
            this.item = null;
        }
    }

    public String getDisplayForPlayer() {
        if (this.item == null) {
            return " ";
        }
        if (this.item.getEnergie() > 0) {
            return "+";
        }
        return "-";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public ItemInterface getItem() {
        return this.item;
    }

    public void setItem(ItemInterface item) {
        this.item = item;
    }

    public boolean isTarget() {
        return this.target;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }
}