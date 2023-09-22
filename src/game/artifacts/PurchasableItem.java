package game.artifacts;

import edu.monash.fit2099.engine.items.Item;
import game.artifacts.trickery.Trickery;

public class PurchasableItem {
    private final Item item;
    private int price;
    private final Trickery trickery;

    public PurchasableItem(Item item, int originalPrice, Trickery trickery){
        this.item = item;
        this.price = originalPrice;
        this.trickery = trickery;
    }

    public Item getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public Trickery getTrickery() {
        return trickery;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    }
