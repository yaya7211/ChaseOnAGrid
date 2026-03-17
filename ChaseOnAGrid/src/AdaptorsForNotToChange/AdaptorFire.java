package AdaptorsForNotToChange;

import notToChange.Fire;
import notToChange.ItemInterface;

public class AdaptorFire implements ItemInterface {
    private final Fire fire;

    public AdaptorFire(Fire fire) {
        this.fire = fire;
    }

    @Override
    public int getEnergie() {
        return this.fire.decreaseEnergy();
    }
}