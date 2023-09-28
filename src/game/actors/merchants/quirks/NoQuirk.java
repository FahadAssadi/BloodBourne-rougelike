package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import game.artifacts.TransactionItem;

public class NoQuirk implements Quirk{

    @Override
    public void perform(Actor actor, TransactionItem transactionItem) {
        // Do Nothing
    }

    @Override
    public boolean doesOccur() {
        return true;
    }
}
