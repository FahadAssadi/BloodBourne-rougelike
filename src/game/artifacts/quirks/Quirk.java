package game.artifacts.quirks;

import game.artifacts.PurchasableItem;

public interface Quirk {

    void perform(PurchasableItem purchasableItem);

    boolean doesOccur();
}
