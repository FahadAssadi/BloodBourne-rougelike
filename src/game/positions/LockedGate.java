package game.positions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UnlockGateAction;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.gamestate.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing a locked gate in the game world.
 * This gate can be locked or unlocked by actions.
 */
public class LockedGate extends Ground implements Resettable {
    private boolean isLocked;
    private List<Action> teleportActions = new ArrayList<>();
    private static final char DEFAULT_DISPLAY_CHAR = '=';
    private static final boolean DEFAULT_LOCKED_STATUS = true;

    /**
     * Constructor for the LockedGate class.
     *
     * @param actions The action associated with teleportation.
     */
    public LockedGate(List<Action> actions){
        super(DEFAULT_DISPLAY_CHAR);
        this.isLocked = DEFAULT_LOCKED_STATUS;
        this.teleportActions = actions;
    }

    /**
     * Unlocks the gate, allowing actors to pass through.
     */
    public void unlockGate(){
        this.isLocked = false;
    }

    /**
     * Checks whether an actor can enter the gate based on its lock status.
     *
     * @param actor The actor attempting to enter the gate.
     * @return true if the gate is unlocked, false if it's locked.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !isLocked;
    }

    /**
     * Locks the LockedGate when resetting.
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESET);
        this.isLocked = DEFAULT_LOCKED_STATUS;
    }

    /**
     * Provides a list of allowable actions for an actor when interacting with the gate.
     * If the gate is locked, it allows the player to unlock it with an UnlockGateAction.
     * If the gate is unlocked and the actor has the Ability.TELEPORTS capability, it allows
     * the actor to use the teleportAction.
     *
     * @param actor     The actor interacting with the gate.
     * @param location  The location of the gate.
     * @param direction The direction from which the actor is approaching the gate.
     * @return A list of allowable actions for the actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();

        if (this.isLocked){
            actions.add(new UnlockGateAction(this));
        } else {
            if (actor.hasCapability(Ability.TELEPORTS)){
                this.teleportActions.forEach((teleportAction) -> {
                    actions.add(teleportAction);
                });
            }
        }

        return actions;
    }
}
