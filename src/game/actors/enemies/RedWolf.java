package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.Runes;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.FollowBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.capabilities.Status;
import game.weather.weathermanager.RainySusceptible;
import game.weather.weathermanager.SunnySusceptible;

/**
 * A class representing a type of enemy called "Red Wolf."
 *
 * The RedWolf class extends the Enemy class and represents a type of enemy in the game.
 * It defines its behaviors, droppable items, and default attributes.
 * The RedWolf class also implements the SunnySusceptible and RainySusceptible interfaces
 * to respond to weather changes.
 *
 * Default attributes:
 * - Name: "Red Wolf"
 * - Display Character: 'r'
 * - Hit Points: 25
 * - Intrinsic Weapon Damage: 15
 * - Intrinsic Weapon Hit Rate: 80%
 * - Intrinsic Weapon Verb: "bites"
 * - Healing Vial Drop Rate: 10%
 * - Rune Drop Amount: 25
 *
 * Behaviors:
 * - AttackBehaviour: The Red Wolf can attack hostile actors.
 * - WanderBehaviour: The Red Wolf can wander randomly.
 *
 * Capabilities:
 * - It has the "RainySusceptible" and "SunnySusceptible" capabilities to respond to weather changes.
 *
 * Created By:
 * @author Kevin Chan
 * Modified by:
 * @author Fahad Assadi
 */
public class RedWolf extends Enemy implements SunnySusceptible, RainySusceptible {
    // Default attributes for the Red Wolf
    private static final String DEFAULT_NAME = "Red Wolf";
    private static final char DEFAULT_DISPLAY_CHAR = 'r';
    private static final int DEFAULT_HITPOINTS = 25;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 15;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 80;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "bites";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 10;
    private static final int DEFAULT_RUNE_DROP_AMOUNT = 25;
    private static final int DEFAULT_SUMMER_DAMAGE_MULTIPLIER = 3;
    private static final int DEFAULT_RAINY_DAMAGE_MULTIPLIER = 1;
    private static final int DEFAULT_ATTACK_BEHAVIOUR_PRIORITY = 1;
    private static final int DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY = 2;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 999;

    /**
     * Default constructor for the RedWolf class.
     */
    public RedWolf() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        registerAsRainySusceptible();
        registerAsSunnySusceptible();
    }

    /**
     * Custom constructor for the RedWolf class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public RedWolf(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        registerAsRainySusceptible();
        registerAsSunnySusceptible();
    }

    /**
     * Adds the AttackBehaviour and WanderBehaviour to the behaviors map.
     */
    @Override
    protected void addBehaviours() {
        this.behaviours.put(DEFAULT_ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour());
        this.behaviours.put(DEFAULT_WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());
    }

    /**
     * Adds droppable items (Healing Vial and Runes) to the droppableItems map with their drop rates.
     */
    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new HealingVial()), DEFAULT_HEAL_VIAL_DROP_RATE);
        this.droppableItems.put(new DropAction(new Runes(DEFAULT_RUNE_DROP_AMOUNT)), DEFAULT_RUNES_DROP_RATE);
    }

    /**
     * Define the intrinsic weapon for the Red Wolf.
     *
     * @return An intrinsic weapon with specified damage and verb.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }

    /**
     * Define allowable actions for the Red Wolf based on the presence of hostile actors.
     *
     * @param otherActor The other actor (usually the player) to check for hostility.
     * @param direction  The direction in which the action is allowed.
     * @param map        The GameMap where the action occurs.
     * @return A list of allowable actions for the Hollow Solider.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            this.behaviours.put(DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Responds to sunny weather conditions by increasing the damage multiplier.
     *
     * @return A message indicating the change in behavior due to sunny weather.
     */
    @Override
    public String sunnyWeather() {
        this.updateDamageMultiplier(DEFAULT_SUMMER_DAMAGE_MULTIPLIER);
        return "The red wolves are becoming more aggressive.";
    }

    /**
     * Responds to rainy weather conditions by decreasing the damage multiplier.
     *
     * @return A message indicating the change in behavior due to rainy weather.
     */
    @Override
    public String rainyWeather() {
        this.updateDamageMultiplier(DEFAULT_RAINY_DAMAGE_MULTIPLIER);
        return "The red wolves are becoming less aggressive.";
    }
}
