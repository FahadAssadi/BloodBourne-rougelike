package game.artifacts.consumables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;


public class Runes extends Item implements Consumable {
    private final static String DEFAULT_DISPLAY_NAME = "Runes";
    private final static char DEFAULT_DISPLAY_CHAR = '$';
    private final static boolean DEFAULT_PORTABLE_STATUS = true;

    private final int runesAmount;

    public Runes(int runesAmount) {
        super(DEFAULT_DISPLAY_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_PORTABLE_STATUS);
        this.runesAmount = runesAmount;
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
    }

    @Override
    public void consume(Actor actor) {
        actor.addBalance(this.runesAmount);
    }

    /**
     * Define allowable actions related to the runes for the owner actor.
     *
     * @param owner The actor who owns the Healing Vial.
     * @return An ActionList containing the allowable actions for the owner actor.
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();

        // Allow the owner actor to consume the Rune
        actions.add(new ConsumeAction(this));

        return actions;
    }
}
