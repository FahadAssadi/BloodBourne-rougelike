package game.artifacts;


/**
 * Interface for items that can be sold by the player
 */
public interface Sellable {
    /**
     * @return The default price that the player sells this item for
     */
    int getSellingPrice();
}
