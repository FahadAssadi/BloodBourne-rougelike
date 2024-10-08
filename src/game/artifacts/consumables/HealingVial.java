package game.artifacts.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.actions.UpgradeAction;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.actors.friendly.merchants.quirks.PricingQuirk;
import game.artifacts.Upgradable;
import game.capabilities.Ability;

/**
 * A specific consumable item representing a Healing Vial in the game.
 */
public class HealingVial extends Item implements Consumable, Sellable, Upgradable {
    private static final String DEFAULT_NAME = "Healing Vial";
    private static final char DEFAULT_DISPLAY_CHAR = 'a';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;
    private static final double DEFAULT_HEALTH_RESTORATION = 0.1;
    private static final int DEFAULT_HEALING_VIAL_PRICE = 35;
    private static final int DEFAULT_UPGRADE_LIMIT = 1;
    private static final int DEFAULT_UPGRADE_PRICE = 250;
    private static final double DEFAULT_UPGRADED_HEALTH_RESTORATION = 0.8;

    private double healthRestoration = DEFAULT_HEALTH_RESTORATION;
    private int upgradeCount = 0;

    /**
     * Constructor for the HealingVial class.
     */
    public HealingVial() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABILITY_STATUS);
    }

    /**
     * @return The default price that the player sells this item for
     */
    @Override
    public int getSellingPrice() {
        return DEFAULT_HEALING_VIAL_PRICE;
    }

    @Override
    public void upgrade() {
        this.upgradeCount++;
        this.healthRestoration = DEFAULT_UPGRADED_HEALTH_RESTORATION;
    }

    @Override
    public boolean isUpgradable() {
        return this.upgradeCount < DEFAULT_UPGRADE_LIMIT;
    }

    @Override
    public int getUpgradePrice() {
        return DEFAULT_UPGRADE_PRICE;
    }

    /**
     * Define the behavior of consuming the Healing Vial to restore the actor's health.
     *
     * @param actor The actor consuming the Healing Vial.
     */
    @Override
    public void consume(Actor actor) {
        // Calculate the amount of health to restore based on a percentage of maximum health
        int healActorBy = (int) (actor.getAttributeMaximum(BaseActorAttributes.HEALTH) * healthRestoration);
        // Heal the actor by the calculated amount
        actor.heal(healActorBy);
        actor.removeItemFromInventory(this);

    }

    /**
     * Define allowable actions related to the Healing Vial for the owner actor.
     *
     * @param owner The actor who owns the Healing Vial.
     * @return An ActionList containing the allowable actions for the owner actor.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();

        // Allow the owner actor to consume the Healing Vial
        actions.add(new ConsumeAction(this));

        return actions;
    }

    /**
     *
     * Define allowable actions that Healing Vial allows its owner do to other actor.
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
                    new PricingQuirk(10, 100) // Modifying the price during the transaction
            ));
        }

        if (otherActor.hasCapability(Ability.UPGRADES)){
            if (this.isUpgradable()){
                actions.add(new UpgradeAction(this));
            }
        }

        return actions;
    }

}
