package game.artifacts;

import edu.monash.fit2099.engine.items.Item;
import game.artifacts.quirks.Quirk;

public class PurchasableItem {
    private final Item item;
    private int price;
    private final Quirk quirk;

    public PurchasableItem(Item item, int originalPrice, Quirk quirk){
        this.item = item;
        this.price = originalPrice;
        this.quirk = quirk;
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public Quirk getTrickery() {
        return quirk;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
