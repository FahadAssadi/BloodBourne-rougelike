package game.artifacts;

import edu.monash.fit2099.engine.items.Item;


/**
 * Class that acts as a wrapper class for an item during a transaction
 */
public class TransactionItem {
    private final Item item;
    private final int price;

    /**
     * Constructor for a transaction item
     * @param item Item in the transaction
     * @param originalPrice Original price of the item in the transaction
     */
    public TransactionItem(Item item, int originalPrice){
        this.item = item;
        this.price = originalPrice;
    }

    /**
     * Getter for transaction item
     *
     * @return Item in the transaction
     */
    public Item getItem() {
        return item;
    }

    /**
     * Getter for the transaction item price
     *
     * @return Original price of the item in the transaction
     */
    public int getPrice() {
        return price;
    }
}
