package game.artifacts.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.TransactionAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.capabilities.TransactionType;

/**
 * A specific consumable item representing a Healing Vial in the game.
 */
public class HealingVial extends Item implements Consumable {
    private static final String DEFAULT_NAME = "Healing Vial";
    private static final char DEFAULT_DISPLAY_CHAR = 'a';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;
    private static final double DEFAULT_HEATH_RESTORATION = 0.1;

    /**
     * Constructor for the HealingVial class.
     */
    public HealingVial() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABILITY_STATUS);
    }

    /**
     * Define the behavior of consuming the Healing Vial to restore the actor's health.
     *
     * @param actor The actor consuming the Healing Vial.
     */
    @Override
    public void consume(Actor actor) {
        // Calculate the amount of health to restore based on a percentage of maximum health
        int healActorBy = (int) (actor.getAttributeMaximum(BaseActorAttributes.HEALTH) * (DEFAULT_HEATH_RESTORATION));
        // Heal the actor by the calculated amount
        actor.heal(healActorBy);

        // Remove the consumed Healing Vial from the actor's inventory
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

        // APPROACH #2: ALLOWING PLAYER TO SELL EACH ITEM IN THEIR INVENTORY ONLY when in vicinity of Traveller
        // Would need to implement this in every single item class
        // actions.add(new TransactionAction(this, TransactionType.SELL, owner, owner, 0));

        return actions;
    }

}
