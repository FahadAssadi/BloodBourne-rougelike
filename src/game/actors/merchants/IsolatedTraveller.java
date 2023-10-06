package game.actors.merchants;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.artifacts.TransactionItem;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.RefreshingFlask;
import game.actors.merchants.quirks.PricingQuirk;
import game.actors.merchants.quirks.Quirk;
import game.actors.merchants.quirks.ScamQuirk;
import game.artifacts.weapons.GreatKnife;
import game.capabilities.Ability;
import game.artifacts.weapons.Broadsword;
import game.capabilities.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * A class representing an isolated traveler who serves as a merchant in the game.
 */
public class IsolatedTraveller extends Actor {
    // Default attributes for the Isolated Traveller
    private static final String DEFAULT_NAME = "Isolated Traveller";
    private static final char DEFAULT_DISPLAY_CHAR = 'ඞ';
    private static final int DEFAULT_HITPOINTS = 200; //randomly assigned, doesn't affect gameplay.
    private static final int DEFAULT_HEALING_VIAL_PRICE = 100;
    private static final int DEFAULT_REFRESHING_FLASK_PRICE = 75;
    private static final int DEFAULT_BROAD_SWORD_PRICE = 250;
    private static final int DEFAULT_GREAT_KNIFE_PRICE = 300;

    /**
     * Constructor for the IsolatedTraveller class with default attributes.
     */
    public IsolatedTraveller() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Ability.TRADES);
        this.addCapability(Status.FRIENDLY);
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
        this.addCapability(Ability.TRADES);
    }

    /**
     * Defines the actions that the Isolated Traveller can perform during its turn.
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

    /**
     * Generates a menu of sellable items along with their associated quirks for the Isolated Traveller.
     *
     * @return A map of sellable items and their associated quirks.
     */
    private Map<TransactionItem, Quirk> generateSellingMenu() {
        Map<TransactionItem, Quirk> sellableItems = new HashMap<>();

        sellableItems.put(new TransactionItem(new HealingVial(), DEFAULT_HEALING_VIAL_PRICE), new PricingQuirk(25, 50));
        sellableItems.put(new TransactionItem(new RefreshingFlask(), DEFAULT_REFRESHING_FLASK_PRICE), new PricingQuirk(10, -20));
        sellableItems.put(new TransactionItem(new Broadsword(), DEFAULT_BROAD_SWORD_PRICE), new ScamQuirk(5));
        sellableItems.put(new TransactionItem(new GreatKnife(), DEFAULT_GREAT_KNIFE_PRICE), new PricingQuirk(5, 300));

        return sellableItems;
    }

    /**
     * Defines the allowable actions for the Isolated Traveller based on the presence of other actors and their direction.
     *
     * @param otherActor The other actor (usually the player) to check for actions.
     * @param direction  The direction in which the action is allowed.
     * @param map        The GameMap where the action occurs.
     * @return A list of allowable actions for the Isolated Traveller, including purchasing items with associated quirks.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        generateSellingMenu().forEach(((purchasableItem, quirk) -> {
            actions.add(new PurchaseAction(purchasableItem, quirk));
        }));

        return actions;
    }
}
