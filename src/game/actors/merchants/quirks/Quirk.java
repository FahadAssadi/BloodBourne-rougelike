package game.actors.merchants.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import game.artifacts.TransactionItem;

public interface Quirk {

    // Takes actor because a vendor might have a quirk that does something to the actor
    // (eg: he kicks the actor after taking his money)
    void perform(Actor actor, TransactionItem transactionItem);

    boolean doesOccur();
}
