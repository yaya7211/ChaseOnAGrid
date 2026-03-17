package AdaptorsForNotToChange;

import notToChange.ItemInterface;
import notToChange.Predator;

public class AdaptorPredator implements ItemInterface {
    private final Predator predator;

    public AdaptorPredator(Predator predator) {
        this.predator = predator;
    }

    @Override
    public int getEnergie() {
        return this.predator.looseEnergy();
    }
}