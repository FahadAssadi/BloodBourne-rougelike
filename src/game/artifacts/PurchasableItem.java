package game.artifacts;

import edu.monash.fit2099.engine.items.Item;

public class PurchasableItem {
    private Item item;
    private int price;

    public PurchasableItem(Item item, int originalPrice){
        this.item = item;
        this.price = originalPrice;
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public void setItem(Item item){
        this.item = item;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
