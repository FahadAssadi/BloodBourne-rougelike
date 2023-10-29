package game.artifacts;


/**
 * An interface for objects that can be upgraded.
 */

public interface Upgradable {

    /**
     * Performs an upgrade operation on the object.
     */
    void upgrade();

    /**
     * Checks whether the object is currently upgradable.
     *
     * @return `true` if the object can be upgraded, `false` otherwise.
     */
    boolean isUpgradable();
    /**
     * Retrieves the price for upgrading the object.
     *
     * @return The price, as an integer, for upgrading the object.
     */
    int getUpgradePrice();
}
