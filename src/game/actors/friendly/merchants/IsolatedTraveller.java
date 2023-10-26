package game.actors.friendly.merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actions.PurchaseAction;
import game.actors.friendly.Friendly;
import game.artifacts.TransactionItem;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.RefreshingFlask;
import game.actors.friendly.merchants.quirks.PricingQuirk;
import game.actors.friendly.merchants.quirks.Quirk;
import game.actors.friendly.merchants.quirks.ScamQuirk;
import game.artifacts.weapons.GreatKnife;
import game.capabilities.Ability;
import game.artifacts.weapons.Broadsword;
import game.capabilities.Status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class representing an isolated traveler who serves as a merchant in the game.
 */
public class IsolatedTraveller extends Friendly implements Listenable{
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

    @Override
    public List<String> getMonologueList(Actor actor) {
        List<String> monologueList = new ArrayList<>();

        monologueList.add("Of course, I will never give you up, valuable customer!");
        monologueList.add("I promise I will never let you down with the quality of the items that I sell.");
        monologueList.add("You can always find me here. I'm never gonna run around and desert you, dear customer!");
        monologueList.add("I'm never gonna make you cry with unfair prices.");
        monologueList.add("Trust is essential in this business. I promise I’m never gonna say goodbye to a valuable customer like you.");
        monologueList.add("Don't worry, I’m never gonna tell a lie and hurt you.");

        if (actor.hasCapability(Status.CARRIES_GIANT_HAMMER)){
            monologueList.add("Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.");
        }

        if (!actor.hasCapability(Status.FELLED_ABXERVYER)){
            monologueList.add("You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.");
        } else {
            if (actor.hasCapability(Status.CARRIES_GIANT_HAMMER)){
                monologueList.add("Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.");
            }
        }

        return monologueList;
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

        actions.add(new ListenAction(this));

        return actions;
    }
}
