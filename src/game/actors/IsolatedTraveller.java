package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SellAction;
import game.actions.TransactionAction;
import game.artifacts.consumables.Bloodberry;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.RefreshingFlask;
import game.capabilities.Ability;
import game.weapons.Broadsword;

import java.util.HashMap;
import java.util.Map;


public class IsolatedTraveller extends Actor {
    // Default attributes for the Isolated Traveller
    private static final String DEFAULT_NAME = "Isolated Traveller";
    private static final char DEFAULT_DISPLAY_CHAR = 'ඞ';
    private static final int DEFAULT_HITPOINTS = 200; //randomly assigned, doesnt affect gameplay.

    protected Map<Item, Integer> sellableItems = new HashMap<>();
    private static final int DEFAULT_VIAL_PRICE = 100;
    private static final int DEFAULT_FLASK_PRICE = 75;
    private static final int DEFAULT_SWORD_PRICE = 250;

    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public IsolatedTraveller(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.TRANSACTS);

        this.populateSellable();
    }

    public IsolatedTraveller() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Ability.TRANSACTS);

        this.populateSellable();
    }

    public void populateSellable() {
        this.sellableItems.put(new HealingVial(), DEFAULT_VIAL_PRICE);
        this.sellableItems.put(new RefreshingFlask(), DEFAULT_FLASK_PRICE);
        this.sellableItems.put(new Broadsword(), DEFAULT_SWORD_PRICE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Ability.TRANSACTS)){
            for (Item item: this.getItemInventory()) {
                actions.add(new TransactionAction(item, this, otherActor));
            }
        }
        return actions;
    }
}
