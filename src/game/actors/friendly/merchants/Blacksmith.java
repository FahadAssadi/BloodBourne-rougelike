package game.actors.friendly.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friendly.Friendly;
import game.capabilities.Ability;
import game.capabilities.Status;


public class Blacksmith extends Friendly {
    // Default attributes for the Blacksmith
    private static final String DEFAULT_NAME = "Blacksmith";
    private static final char DEFAULT_DISPLAY_CHAR = 'B';
    private static final int DEFAULT_HITPOINTS = 200; //randomly assigned, doesn't affect gameplay.


    /**
     * The constructor of the Blacksmith class.
     *
     * @param name        the name of the Blacksmith
     * @param displayChar the character that will represent the Blacksmith in the display
     * @param hitPoints   the Blacksmith's starting hit points
     */
    public Blacksmith(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.UPGRADES);
    }
    public Blacksmith() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Ability.UPGRADES);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }
}
