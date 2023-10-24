package game.artifacts.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.actions.UpgradeAction;
import game.artifacts.LimitedUpgrade;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.actors.friendly.merchants.quirks.ScamQuirk;
import game.capabilities.Ability;

/**
 * A specific consumable item representing a Refreshing Vial in the game.
 */
public class RefreshingFlask extends Item implements Consumable, Sellable, LimitedUpgrade {
    private static final String DEFAULT_NAME = "Refreshing Flask";
    private static final char DEFAULT_DISPLAY_CHAR = 'u';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;
    private static final double DEFAULT_STAMINA_RESTORATION = 0.2;
    private static final int DEFAULT_REFRESHING_FLASK_PRICE = 25;
    private static  final int DEFAULT_UPGRADABLE_PRICE =175;


    /*
     Keeps track of the number of times the item has been upgraded
     */
    private int upgradeCount;

    private static final int DEFAULT_REFRESHING_FLASK_UPGRADE_LIMIT = 1;
    private static final double DEFAULT_UPGRADED_STAMINA_RESTORATION = 1.0;

    private static double STAMINA_RESTORATION = DEFAULT_STAMINA_RESTORATION;

    /**
     * Constructor for the RefreshingFlask class.
     */
    public RefreshingFlask() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABILITY_STATUS);
    }

    /**
     * @return The default price that the player sells this item for
     */
    @Override
    public int getSellingPrice() {
        return DEFAULT_REFRESHING_FLASK_PRICE;
    }

    /**
     * Define the behavior of consuming the Refreshing Vial to restore the actor's stamina.
     *
     * @param actor The actor consuming the Refreshing Vial.
     */
    @Override
    public void consume(Actor actor) {
        // Calculate the amount of stamina to restore based on a percentage of maximum stamina
        int restoreStaminaBy = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * STAMINA_RESTORATION);
        // Increase the actor's stamina by the calculated amount
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, restoreStaminaBy);
        // Remove the consumed Refreshing Vial from the actor's inventory
        actor.removeItemFromInventory(this);
    }

    @Override
    public void upgrade() {
        if (this.canUpgrade()) {
            STAMINA_RESTORATION = DEFAULT_UPGRADED_STAMINA_RESTORATION;
            this.upgradeCount += 1;
        }
    }

    @Override
    public int getUpgradablePrice() {
        return DEFAULT_UPGRADABLE_PRICE;
    }

    @Override
    public int getUpgradeLimit() {
        return DEFAULT_REFRESHING_FLASK_UPGRADE_LIMIT;
    }

    @Override
    public boolean canUpgrade() {
        return this.upgradeCount < this.getUpgradeLimit();
    }

    /**
     * Define allowable actions related to the Refreshing Vial for the owner actor.
     *
     * @param owner The actor who owns the Refreshing Vial.
     * @return An ActionList containing the allowable actions for the owner actor.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();

        actions.add(new ConsumeAction(this));

        return actions;
    }

    /**
     *
     * Define allowable actions that Refreshing Flask allows its owner do to other actor.
     * @param otherActor the other actor
     * @param location   the location of the other actor
     * @return An ActionList containing the allowable actions for the owner actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();

        /*
        Allow actor to Sell this item if they meet another actor who trades
         */
        if (otherActor.hasCapability(Ability.TRADES)) {
            /*
             Add a SellAction that takes in:
             - the Transaction Item, which takes in
                - this item
                - the Player's predefined selling price
             - a quirk (trick) that the trader may play on the player during the transaction
             */
            actions.add(new SellAction(
                    new TransactionItem(this, this.getSellingPrice()),
                    new ScamQuirk(50) // Scamming the player by taking the item without paying
            ));
        }

        if (otherActor.hasCapability(Ability.UPGRADES)) {
            actions.add(new UpgradeAction(this));
        }

        return actions;
    }
}
