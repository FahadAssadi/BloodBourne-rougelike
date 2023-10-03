package game.artifacts;

import edu.monash.fit2099.engine.items.Item;

public class TransactionItem {
    private final Item item;
    private final int price;

    public TransactionItem(Item item, int originalPrice){
        this.item = item;
        this.price = originalPrice;
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }
}
