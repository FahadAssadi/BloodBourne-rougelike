package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;
import game.gamestate.Resettable;
import game.misc.Utility;
import game.gamestate.EntityManager;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * An abstract class representing enemies in the game.
 *
 * This abstract class extends the Actor class from the game engine and is used as a base class
 * for defining various enemy types in the game. It provides functionality for adding behaviors and
 * droppable items to enemies.
 *
 * @author Fahad Assadi
 */
public abstract class Enemy extends Actor implements Resettable {
    /**
     * A map to store behaviors, where the key is an integer priority
     */
    protected Map <Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * A map to store droppable items (as a Drop Action) with their chance of dropping as a percentage
     */
    protected Map<DropAction, Integer> droppableItems = new HashMap<>();

    /**
     * Default drop rate for runes (100%)
     */
    protected static final int DEFAULT_RUNES_DROP_RATE = 100;

    /**
     * Constructor for the Enemy class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        this.addBehaviours();
        this.addDroppableItems();
        this.addCapability(Status.HOSTILE);

        // Add enemy to the entity manager
        registerResettable();
    }

    /**
     * Abstract method to be implemented by concrete enemy classes to add behaviors.
     * This method should populate the 'behaviours' map with various behaviors.
     */
    protected abstract void addBehaviours();

    /**
     * Abstract method to be implemented by concrete enemy classes to add droppable items.
     * This method should populate the 'droppableItems' map with items that can be dropped
     * by the enemy upon defeat, along with their drop rates.
     */
    protected abstract void addDroppableItems();

    /**
     * Overrides the unconscious method to handle the dropping of items when the enemy is defeated.
     * It uses the 'droppableItems' map to determine which items to drop and their drop rates.
     *
     * @param actor The actor that defeated this enemy.
     * @param map   The game map in which the enemy was defeated.
     * @return A message indicating the result of the unconscious action.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        this.droppableItems.forEach(
            (dropAction, chance) ->  {
                boolean dropActionOccurs = Utility.getRandomEventOccurs(chance);
                if (dropActionOccurs) {
                    dropAction.execute(this, map);
                }
            }
        );
        return super.unconscious(actor, map);
    }

    @Override
    public void reset() {
        // Add the RESET status to the actor (used in playTurn to remove it from the map)
        this.addCapability(Status.RESET);
    }

    /**
     * Determine the action the enemy should perform during its turn.
     *
     * @param actions    A collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn. Can be used in conjunction with Action.getNextAction().
     * @param map        The map containing the Actor.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action that the enemy can perform in that iteration or null if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // All spawned enemies (not including bosses) will be removed from the map if they have the RESET status
        if (this.hasCapability(Status.RESET)) {
            if (!this.hasCapability(Status.BOSS)) {
                map.removeActor(this);
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);

            if (action != null){
                return action;
            }
        }
        // If no valid action is found, return a DoNothingAction to represent inaction
        return new DoNothingAction();
    }
}
