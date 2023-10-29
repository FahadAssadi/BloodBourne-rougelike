package game.actors.friendly;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.Status;

/**
 * The abstract class representing a friendly NPC like Blacksmith.
 */
public abstract class Friendly extends Actor {
    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Friendly(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.FRIENDLY);
    }

    /**
     * Defines the actions that a Friendly can perform during its turn.
     *
     * @param actions    The list of allowable actions for the Isolated Traveller.
     * @param lastAction The last action performed by the Isolated Traveller.
     * @param map        The GameMap where the Isolated Traveller is located.
     * @param display    The display used to show messages.
     * @return A DoNothingAction since the Isolated Traveller does not perform actions in this method.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
