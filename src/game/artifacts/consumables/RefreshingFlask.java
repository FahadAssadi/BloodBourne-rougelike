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
import game.actors.merchants.quirks.ScamQuirk;
import game.capabilities.Ability;

/**
 * A specific consumable item representing a Refreshing Vial in the game.
 */
public class RefreshingFlask extends Item implements Consumable, Sellable {
    private static final String DEFAULT_NAME = "Refreshing Flask";
    private static final char DEFAULT_DISPLAY_CHAR = 'u';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;
    private static final double DEFAULT_STAMINA_RESTORATION = 0.2;
    private static final int DEFAULT_REFRESHING_FLASK_PRICE = 25;


    /**
     * Constructor for the RefreshingFlask class.
     */
    public RefreshingFlask() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABILITY_STATUS);
    }

    /**
     * Define the behavior of consuming the Refreshing Vial to restore the actor's stamina.
     *
     * @param actor The actor consuming the Refreshing Vial.
     */
    @Override
    public void consume(Actor actor) {
        // Calculate the amount of stamina to restore based on a percentage of maximum stamina
        int restoreStaminaBy = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * DEFAULT_STAMINA_RESTORATION);
        // Increase the actor's stamina by the calculated amount
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, restoreStaminaBy);
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
                    new TransactionItem(this, DEFAULT_REFRESHING_FLASK_PRICE),
                    new ScamQuirk(50)
            ));
        }

        return actions;
    }
}
