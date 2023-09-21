package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.TransactionAction;
import game.artifacts.consumables.Bloodberry;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.RefreshingFlask;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.capabilities.TransactionType;
import game.displays.FancyMessage;
import game.weapons.Broadsword;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Fahad Assadi
 */
public class Player extends Actor {
    // Default attributes for the Player
    private static final String DEFAULT_NAME = "The Abstracted One";
    private static final char DEFAULT_DISPLAY_CHAR = '@';
    private static final int DEFAULT_HITPOINTS = 150;
    private static final int DEFAULT_STAMINA = 200;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 15;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "punches";
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 80;
    private static final int DEFAULT_STAMINA_RESTORATION_PERCENTAGE = 1;

    protected Map<Item, Integer> sellableItems = new HashMap<>();
    private static final int DEFAULT_VIAL_PRICE = 35;
    private static final int DEFAULT_FLASK_PRICE = 25;
    private static final int DEFAULT_SWORD_PRICE = 100;
    private static final int DEFAULT_BERRY_PRICE = 10;

    /**
     * Default constructor for the Player class.
     * It initializes the Player with default attributes.
     */
    public Player(){
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(DEFAULT_STAMINA));

        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.TELEPORTS);
        this.addCapability(Ability.WALKS_SAFE_TILES);
        this.addCapability(Ability.TRANSACTS);

        this.populateSellable();
    }

    /**
     * Constructor for the Player class with custom attributes.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));

        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.TELEPORTS);
        this.addCapability(Ability.WALKS_SAFE_TILES);
        this.addCapability(Ability.TRANSACTS);

        this.populateSellable();
    }

    public void populateSellable() {
        this.sellableItems.put(new HealingVial(), DEFAULT_VIAL_PRICE);
        this.sellableItems.put(new RefreshingFlask(), DEFAULT_FLASK_PRICE);
        this.sellableItems.put(new Broadsword(), DEFAULT_SWORD_PRICE);
        this.sellableItems.put(new Bloodberry(), DEFAULT_BERRY_PRICE);
    }

    /**
     * Get the intrinsic weapon of the Player.
     * @return An intrinsic weapon object with default damage, verb, and hit rate.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(
                DEFAULT_INTRINSIC_WEAPON_DAMAGE,
                DEFAULT_INTRINSIC_WEAPON_VERB,
                DEFAULT_INTRINSIC_WEAPON_HITRATE
        );
    }

    /**
     * Restore the Player's stamina by a certain percentage.
     *
     * @param staminaPercentage Percentage of stamina to restore
     */
    private void restoreStamina(int staminaPercentage) {
        int increaseStaminaBy = (int) Math.round(this.getAttributeMaximum(BaseActorAttributes.STAMINA) * (double) staminaPercentage/100);
        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, increaseStaminaBy);
    }

    /**
     * Handle the Player becoming unconscious due to natural causes.
     *
     * @param map GameMap where the Player is located
     * @return String indicating the result of becoming unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        this.hurt(this.getAttribute(BaseActorAttributes.HEALTH));
        new Display().println(FancyMessage.YOU_DIED);

        return super.unconscious(map);
    }

    /**
     * Handle the Player becoming unconscious due to another actor's actions.
     *
     * @param actor The actor causing the Player to become unconscious
     * @param map   GameMap where the Player and the other actor are located
     * @return String indicating the result of becoming unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        new Display().println(FancyMessage.YOU_DIED);

        return super.unconscious(actor,map);
    }

    /**
     * The Player's play turn logic.
     *
     * @param actions    List of possible actions for the Player
     * @param lastAction The last action taken by the Player
     * @param map        GameMap where the Player is located
     * @param display    Display object for rendering game output
     * @return An Action object representing the Player's chosen action for the turn
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Increase the stamina of the player by a percentage each turn
        this.restoreStamina(DEFAULT_STAMINA_RESTORATION_PERCENTAGE);

        // Printing the current hit points and stamina of the player
        this.displayPlayerStats(display);

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Display the current hit points and stamina of the Player.
     */
    private void displayPlayerStats(Display display) {
        display.println(String.format(
                "%s \nHP: %s/%s \nStamina: %s/%s \nRunes: %d",

                this.name,
                this.getAttribute(BaseActorAttributes.HEALTH),
                this.getAttributeMaximum(BaseActorAttributes.HEALTH),
                this.getAttribute(BaseActorAttributes.STAMINA),
                this.getAttributeMaximum(BaseActorAttributes.STAMINA),
                this.getBalance()
        ));
    }


    // APPROACH #1: But THIS DOESN'T GET CALLED IN processActorTurn
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        if(otherActor.hasCapability(Ability.TRANSACTS)) {
            this.sellableItems.forEach(
                (item, price) -> {
                    actions.add(new TransactionAction(item, TransactionType.SELL, this, otherActor, price));
                }
            );
        }

        return actions;
    }
}
