package game.artifacts.quirks;

import game.artifacts.TransactionItem;

public class NoQuirk implements Quirk{

    @Override
    public void perform(TransactionItem transactionItem) {
        // Do Nothing
    }

    @Override
    public boolean doesOccur() {
        return true;
    }
}
