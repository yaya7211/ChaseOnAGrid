package CoreGame;

import notToChange.ItemInterface;

public class Item implements ItemInterface {
    private final int energy;

    public Item(int energy) {
        this.energy = energy;
    }

    @Override
    public int getEnergie() {
        return this.energy;
    }
}
