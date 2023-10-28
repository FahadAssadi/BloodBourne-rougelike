package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.artifacts.consumables.Runes;
import game.capabilities.Ability;
import game.capabilities.Status;
import game.gamestate.EntityManager;
import game.gamestate.Resettable;
import game.misc.displays.FancyMessage;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Fahad Assadi
 */
public class Player extends Actor implements Resettable {
    // Default attributes for the Player
    private static final String DEFAULT_NAME = "The Abstracted One";
    private static final char DEFAULT_DISPLAY_CHAR = '@';
    private static final int DEFAULT_HITPOINTS = 150;
    private static final int DEFAULT_STAMINA = 200;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 15;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "punches";
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 80;
    private static final int DEFAULT_STAMINA_RESTORATION_PERCENTAGE = 1;

    private GameMap currentMap;


    private  MoveActorAction respawnAction;



    /**
     * Default constructor for the Player class.
     * It initializes the Player with default attributes.
     */
    public Player(){
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(DEFAULT_STAMINA));
        registerResettable();
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.TELEPORTS);
        this.addCapability(Ability.WALKS_SAFE_TILES);
    }

    /**
     * Constructor for the Player class with custom attributes.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     * @param stamina   Player's starting number of stamina
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
        registerResettable();
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(Ability.TELEPORTS);
        this.addCapability(Ability.WALKS_SAFE_TILES);
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
    public void setRespawnAction(MoveActorAction respawnAction) {
        this.respawnAction = respawnAction;
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
     * Resets values of Player attributes to maximum.
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESET);

        int maxStamina = this.getAttributeMaximum(BaseActorAttributes.STAMINA);
        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE, maxStamina);

        int maxHealth = this.getAttributeMaximum(BaseActorAttributes.HEALTH);
        this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, maxHealth);

        int balance = this.getBalance();
        this.deductBalance(balance);

    }

    /**
     * Handle the Player becoming unconscious due to natural causes.
     * Drops the contents of the Player's wallet at their last location.
     *
     * @param map GameMap where the Player is located
     * @return String indicating the result of becoming unconscious
     */
    @Override
    public String unconscious(GameMap map) {
        EntityManager.getEntityManager().resetEntities();
        map.locationOf(this).addItem(new Runes(this.getBalance()));
        this.respawnAction.execute(this,map);
        new Display().println(FancyMessage.YOU_DIED);
        return this + " ceased to exist.";
    }

    /**
     * Handle the Player becoming unconscious due to another actor's actions.
     * Drops the contents of the Player's wallet at their last location.
     *
     * @param actor The actor causing the Player to become unconscious
     * @param map   GameMap where the Player and the other actor are located
     * @return String indicating the result of becoming unconscious
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        new Display().println(FancyMessage.YOU_DIED);
        map.locationOf(this).addItem(new Runes(this.getBalance()));
        EntityManager.getEntityManager().resetEntities();
        this.respawnAction.execute(this,map);
        return this + " met their demise in the hand of " + actor;
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

    // All Dialogue Affecting Attributes
    public Boolean hasDefeatedAbxervyer() {
        return this.hasCapability(Status.DEFEATED_ABXERVYER);
    }

    public Boolean hasGreatKnife() {
        return this.hasCapability(Status.CARRIES_GREAT_KNIFE);
    }

    public Boolean hasGiantHammer() {
        return this.hasCapability(Status.CARRIES_GIANT_HAMMER);
    }

}
