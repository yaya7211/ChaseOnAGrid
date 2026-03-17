package AdaptorsForNotToChange;

import notToChange.Fruit;
import notToChange.ItemInterface;

public class AdaptorFruit implements ItemInterface {
    private final Fruit fruit;

    public AdaptorFruit(Fruit fruits) {
        this.fruit = fruits;
    }

    @Override
    public int getEnergie() {
        return this.fruit.gainEnergy();
    }
}