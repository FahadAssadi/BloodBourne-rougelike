package game.artifacts.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.capabilities.Status;
import game.gamestate.Resettable;


/**
 * A specific consumable item representing Runes, the currency, in the game.
 */
public class Runes extends Item implements Consumable, Resettable {
    private final static String DEFAULT_DISPLAY_NAME = "Runes";
    private final static char DEFAULT_DISPLAY_CHAR = '$';
    private final static boolean DEFAULT_PORTABLE_STATUS = true;

    private final int runesAmount;

    /**
     * Constructor for the Runes class
     *
     * @param runesAmount Amount of runes
     */
    public Runes(int runesAmount) {
        super(DEFAULT_DISPLAY_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABLE_STATUS);
        this.runesAmount = runesAmount;
        registerResettable();
    }

    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param runesAmount the amount of runes gained by consuming it.
     */
    public Runes(String name, char displayChar, boolean portable, int runesAmount) {
        super(name, displayChar, portable);
        this.runesAmount = runesAmount;
        registerResettable();
    }

    /**
     * Define the behavior of consuming the Runes to add to the player's balance.
     *
     * @param actor The actor consuming the Runes.
     */
    @Override
    public void consume(Actor actor) {
        actor.addBalance(this.runesAmount);
    }

    /**
     * Define allowable actions related to the runes for the owner actor.
     *
     * @param owner The actor who owns the Runes.
     * @return An ActionList containing the allowable actions for the owner actor.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();

        // Allow the owner actor to consume the Runes
        actions.add(new ConsumeAction(this));

        return actions;
    }

    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESET))
        {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public void reset() {
        this.addCapability(Status.RESET);
    }
}
