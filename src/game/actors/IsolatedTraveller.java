package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.artifacts.PurchasableItem;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.RefreshingFlask;
import game.artifacts.quirks.PricingQuirk;
import game.artifacts.quirks.ScamQuirk;
import game.capabilities.Ability;
import game.artifacts.weapons.Broadsword;

import java.util.ArrayList;
import java.util.List;


public class IsolatedTraveller extends Actor {
    // Default attributes for the Isolated Traveller
    private static final String DEFAULT_NAME = "Isolated Traveller";
    private static final char DEFAULT_DISPLAY_CHAR = 'ඞ';
    private static final int DEFAULT_HITPOINTS = 200; //randomly assigned, doesn't affect gameplay.

    protected List<PurchasableItem> sellableItems = sellableItems = new ArrayList<>();
    private static final int DEFAULT_HEALING_VIAL_PRICE = 100;
    private static final int DEFAULT_REFRESHING_FLASK_PRICE = 75;
    private static final int DEFAULT_BROAD_SWORD_PRICE = 250;

    public IsolatedTraveller() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Ability.TRANSACTS);

        this.populateSellable();
    }

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

    public void populateSellable() {
        this.sellableItems.add(new PurchasableItem(new HealingVial(), DEFAULT_HEALING_VIAL_PRICE, new PricingQuirk(25, 50)));
        this.sellableItems.add(new PurchasableItem(new RefreshingFlask(), DEFAULT_REFRESHING_FLASK_PRICE, new PricingQuirk(10, -20)));
        this.sellableItems.add(new PurchasableItem(new Broadsword(), DEFAULT_BROAD_SWORD_PRICE, new ScamQuirk(5)));
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        for (PurchasableItem purchasableItem : this.sellableItems){
            actions.add(new PurchaseAction(purchasableItem));
        }

        return actions;
    }
}
