package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.artifacts.OldKey;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.misc.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class representing enemies in the game.
 * @author Fahad Assadi
 */
public abstract class Enemy extends Actor {
    // A map to store behaviors, where the key is an integer priority
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    // A map to store droppable items (as a Drop Action) with their chance of dropping as a percentage
    protected Map<DropAction, Integer> droppableItems = new HashMap<>();


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
        this.addBehaviour(0, new AttackBehaviour());
        this.addBehaviour(2, new WanderBehaviour());
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
