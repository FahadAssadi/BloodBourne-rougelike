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
import game.artifacts.quirks.NoQuirk;
import game.artifacts.quirks.PricingQuirk;
import game.capabilities.Ability;

public class Bloodberry extends Item implements Consumable, Sellable {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */

    private static final String DEFAULT_NAME = "Bloodberry";
    private static final char DEFAULT_DISPLAY_CHAR = '*';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;
    private static final int DEFAULT_MAXHEALTH_INCREASE = 5;
    private static final int DEFAULT_BLOODBERRY_PRICE = 10;


    public Bloodberry(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public Bloodberry() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABILITY_STATUS);
    }


    @Override
    public void consume(Actor actor) {
        // Increase the actor's max health by the calculated amount
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH,ActorAttributeOperations.INCREASE,DEFAULT_MAXHEALTH_INCREASE);
        // Remove the consumed Refreshing Vial from the actor's inventory
        actor.removeItemFromInventory(this);
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

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Ability.TRADES)) {
            actions.add(new SellAction(
                    new TransactionItem(this, DEFAULT_BLOODBERRY_PRICE),
                    new NoQuirk()
            ));
        }

        return actions;
    }

}
