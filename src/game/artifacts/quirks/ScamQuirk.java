package game.artifacts.quirks;

import game.artifacts.TransactionItem;
import game.misc.Utility;

public class ScamQuirk implements Quirk {
    private final double probability;

    public ScamQuirk(double probability) {
        this.probability = probability;
    }

    @Override
    public void perform(TransactionItem transactionItem) {
        transactionItem.setItem(null);
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
