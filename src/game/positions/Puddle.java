package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.artifacts.consumables.Consumable;
import game.capabilities.Status;

/**
 * A special type of ground representing a Puddle
 * Players can drink from this.
 */
public class Puddle extends Ground implements Consumable {
    private static final char DEFAULT_DISPLAY_CHAR = '~';

    private static final int DEFAULT_HEATH_RESTORATION = 1;
    private static final double DEFAULT_STAMINA_RESTORATION = 0.1;

    /**
     * Constructor for the Puddle Class
     * Initializes the ground with the default display character.
     */
    public Puddle() {
        super(DEFAULT_DISPLAY_CHAR);
    }

    /**
     * Define the behavior of consuming the Puddle to restore the actor's health and stamina.
     *
     * @param actor The actor consuming the Puddle
     */
    @Override
    public void consume(Actor actor) {
        actor.heal(DEFAULT_HEATH_RESTORATION);

        int restoreStaminaBy = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * DEFAULT_STAMINA_RESTORATION);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, restoreStaminaBy);
    }

    /**
     * Define allowable actions related to the Puddle for the owner actor.
     *
     * @param actor The actor who is on the Puddle.
     * @return An ActionList containing the allowable actions for the owner actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();

        if (location.containsAnActor() && actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            // Allow the owner actor to consume the Puddle
            actions.add(new ConsumeAction(this));
        }

        return actions;
    }

    @Override
    public String toString() {
        return "Water Puddle";
    }
}
