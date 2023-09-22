package game.artifacts.quirks;

import game.artifacts.PurchasableItem;
import game.misc.Utility;

public class PricingQuirk implements Quirk {
    private final double probability;
    private final double pricePercentage;

    public PricingQuirk(double probability, double pricePercentage) {
        this.probability = probability;
        this.pricePercentage = pricePercentage;
    }

    @Override
    public void perform(PurchasableItem purchasableItem) {
        int priceChange = (int) (purchasableItem.getPrice() * (1 + this.pricePercentage/100));

        purchasableItem.setPrice(priceChange);
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
