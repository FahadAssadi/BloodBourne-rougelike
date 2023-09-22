package game.artifacts.quirks;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.artifacts.PurchasableItem;

public interface Quirk {

    void perform(Actor actor, Item item, int price);


}
