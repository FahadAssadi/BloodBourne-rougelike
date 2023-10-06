package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.consumables.Runes;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.FollowBehaviour;
import game.actors.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.items.DropAction;
import game.artifacts.consumables.HealingVial;
import game.capabilities.Status;
import game.weather.weathermanager.RainySusceptible;

/**
 * A class that represents a special type of enemy called "Forest Keeper."
 *
 * The ForestKeeper class extends the Enemy class and represents a unique enemy in the game.
 * It defines its behaviors, droppable items, and special characteristics when it's raining.
 *
 * Default attributes:
 * - Name: "Forest Keeper"
 * - Display Character: '8'
 * - Hit Points: 125
 * - Intrinsic Weapon Damage: 25
 * - Intrinsic Weapon Hit Rate: 75%
 * - Intrinsic Weapon Verb: "knocks"
 * - Healing Vial Drop Rate: 20%
 * - Rune Drop Amount: 50
 * - Healing Points When Rainy: 10
 *
 * Behaviors:
 * - AttackBehaviour: The Forest Keeper can attack hostile actors.
 * - WanderBehaviour: The Forest Keeper can wander randomly.
 *
 * Capabilities:
 * - It has the "HOSTILE" capability.
 *
 * Special Interfaces:
 * - It implements the "RainySusceptible" interface, which allows it to heal when it's raining.
 *
 * Created by:
 * Modified by: Fahad Assadi
 */
public class ForestKeeper extends Enemy implements RainySusceptible {

    // Default attributes for the Forest Keeper
    private static final String DEFAULT_NAME = "Forest Keeper";
    private static final char DEFAULT_DISPLAY_CHAR = '8';
    private static final int DEFAULT_HITPOINTS = 125;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 25;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 75;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "knocks";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 20;
    private static final int DEFAULT_RUNE_DROP_AMOUNT = 50;
    private static final int DEFAULT_HEAL_POINTS_WHEN_RAINY = 10;
    private static final int DEFAULT_ATTACK_BEHAVIOUR_PRIORITY = 1;
    private static final int DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY = 2;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 999;

    /**
     * Default constructor for the Forest Keeper Class.
     */
    public ForestKeeper() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        registerAsRainySusceptible();
    }

    /**
     * Custom constructor for the Forest Keeper Class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public ForestKeeper(String name, char displayChar, int hitPoints) {
        super(name,displayChar,hitPoints);
        registerAsRainySusceptible();
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
     * Returns the intrinsic weapon for the Forest Keeper.
     *
     * @return An IntrinsicWeapon representing the Forest Keeper's attack.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }


    /**
     * Define allowable actions for the Forest Keeper based on the presence of hostile actors.
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
     * Overrides the rainyWeather method from the RainySusceptible interface.
     * The Forest Keeper heals when it's raining.
     *
     * @return A message indicating the healing effect when it's rainy.
     */
    @Override
    public String rainyWeather() {
        this.heal(DEFAULT_HEAL_POINTS_WHEN_RAINY);
        return this + " feels rejuvenated.";
    }
}
