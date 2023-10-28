package game.artifacts.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.SellAction;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.actors.friendly.merchants.quirks.NoQuirk;
import game.capabilities.Ability;

/**
 * A specific consumable item representing a Bloodberry in the game.
 */
public class Bloodberry extends Item implements Consumable, Sellable {
    private static final String DEFAULT_NAME = "Bloodberry";
    private static final char DEFAULT_DISPLAY_CHAR = '*';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;
    private static final int DEFAULT_MAXHEALTH_INCREASE = 5;
    private static final int DEFAULT_BLOODBERRY_PRICE = 10;

    /***
     * Constructor for the Bloodberry class.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Bloodberry(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /***
     * Constructor (default) for the Bloodberry class.
     *
     */
    public Bloodberry() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABILITY_STATUS);
    }

    /**
     * @return The default price that the player sells this item for
     */
    @Override
    public int getSellingPrice() {
        return DEFAULT_BLOODBERRY_PRICE;
    }

    /**
     * Define the behavior of consuming the Bloodberry to increase the actor's max health.
     *
     * @param actor The actor consuming the Bloodberry.
     */
    @Override
    public void consume(Actor actor) {
        // Increase the actor's max health by the calculated amount
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH,ActorAttributeOperations.INCREASE,DEFAULT_MAXHEALTH_INCREASE);
        // Remove the consumed Refreshing Vial from the actor's inventory
        actor.removeItemFromInventory(this);
    }

    /**
     * Define allowable actions related to the Bloodberry for the owner actor.
     *
     * @param owner The actor who owns the Bloodberry.
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
     * Define allowable actions that Bloodberry allows its owner do to other actor.
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
                    new NoQuirk()
            ));
        }

        return actions;
    }


}
