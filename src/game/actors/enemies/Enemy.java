package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class representing enemies in the game.
 * @author Fahad Assadi
 */
public abstract class Enemy extends Actor {
    // A map to store behaviors, where the key is an integer priority
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor for the Enemy class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        // Initialize default behaviors for enemies
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
    }

    /**
     * Add a behavior to the enemy.
     *
     * @param key       The priority key for the behavior.
     * @param behaviour The behavior to add.
     */
    protected void addBehaviour(int key, Behaviour behaviour){
        this.behaviours.put(key, behaviour);
    }

    /**
     * Remove a behavior from the enemy.
     *
     * @param key The priority key of the behavior to remove.
     */
    protected void removeBehaviour(int key){
        this.behaviours.remove(key);
    }

    /**
     * Define the behavior when the enemy actor becomes unconscious by natural causes.
     * @param map where the actor fell unconscious
     * @return A string representing the result of becoming unconscious.
     */
    @Override
    public String unconscious(GameMap map) {
        this.hurt(this.getAttribute(BaseActorAttributes.HEALTH));

        return super.unconscious(map);
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
