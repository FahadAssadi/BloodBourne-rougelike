package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;
import game.positions.LockedGate;

/**
 * An action that allows an actor to unlock a locked gate.
 */
public class UnlockGateAction extends Action {
    /**
     * The locked gate to be unlocked.
     */
    private final LockedGate lockedGate;

    /**
     * Constructor for the UnlockGateAction class.
     *
     * @param lockedGate The locked gate to be unlocked.
     */
    public UnlockGateAction(LockedGate lockedGate){
        this.lockedGate = lockedGate;
    }

    /**
     * Execute the action, allowing the actor to unlock the gate if they possess the key.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap where the action occurs.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Check if the actor has the capability to possess the key
        if (actor.hasCapability(Status.POSSESS_KEY)){
            this.lockedGate.unlockGate();
            return "Gate is now unlocked.";
        }
        // If the actor doesn't possess the key, the gate remains locked
        return "Gate is locked shut.";
    }

    /**
     * Get a menu description for the action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Gate";
    }
}
