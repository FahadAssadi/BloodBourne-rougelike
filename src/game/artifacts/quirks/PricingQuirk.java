package game.artifacts.quirks;

import game.artifacts.TransactionItem;
import game.misc.Utility;

public class PricingQuirk implements Quirk {
    private final double probability;
    private final double pricePercentage;

    public PricingQuirk(double probability, double pricePercentage) {
        this.probability = probability;
        this.pricePercentage = pricePercentage;
    }

    @Override
    public void perform(TransactionItem transactionItem) {
        int priceChange = (int) (transactionItem.getPrice() * (1 + this.pricePercentage/100));

        transactionItem.setPrice(priceChange);
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
