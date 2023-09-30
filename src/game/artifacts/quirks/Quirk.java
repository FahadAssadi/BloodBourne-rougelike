package game.artifacts.quirks;

import game.artifacts.TransactionItem;

public interface Quirk {

    void perform(TransactionItem transactionItem);

    boolean doesOccur();
}
