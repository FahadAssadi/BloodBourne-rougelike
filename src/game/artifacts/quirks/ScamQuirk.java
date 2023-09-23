package game.artifacts.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import game.artifacts.TransactionItem;
import game.misc.Utility;

public class ScamQuirk implements Quirk {
    private final double probability;

    public ScamQuirk(double probability) {
        this.probability = probability;
    }

    @Override
    public void perform(Actor actor, TransactionItem transactionItem) {
        transactionItem.setItem(null);
    }

    @Override
    public boolean doesOccur() {
        return Utility.getRandomEventOccurs(this.probability);
    }
}
