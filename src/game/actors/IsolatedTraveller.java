package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

public class IsolatedTraveller extends Actor {
    // Default attributes for the Isolated Traveller
    private static final String DEFAULT_NAME = "Isolated Traveller";
    private static final char DEFAULT_DISPLAY_CHAR = 'ඞ';
    private static final int DEFAULT_HITPOINTS = 200; //randomly assigned, doesnt affect gameplay.

//    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 10;

    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public IsolatedTraveller(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }
    public IsolatedTraveller() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
