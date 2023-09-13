package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.vials.Consumable;

/**
 * An action that allows an actor to consume a consumable item.
 */
public class ConsumeAction extends Action {
    private final Consumable consumable;

    /**
     * Constructor for the ConsumeAction class.
     *
     * @param consumable The consumable item to be consumed.
     */
    public ConsumeAction(Consumable consumable){
        this.consumable = consumable;
    }

    /**
     * Execute the action, allowing the actor to consume the consumable item.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap where the action occurs.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.consumable.consume(actor);

        return actor + " consumes the " + this.consumable;
    }

    /**
     * Get a menu description for the action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + this.consumable;
    }
}
