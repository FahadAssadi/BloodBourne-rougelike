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
import game.weather.WeatherSusceptible;

/**
 * A class that represents a special type of enemy called "Forest Keeper"
 */
public class ForestKeeper extends Enemy implements WeatherSusceptible {

    // Default attributes for the Forest Keeper
    private static final String DEFAULT_NAME = "Forest Keeper";
    private static final char DEFAULT_DISPLAY_CHAR = '8';
    private static final int DEFAULT_HITPOINTS = 125;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 25;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 75;

    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "knocks";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 20;

    private static final int DEFAULT_RUNE_DROP_AMOUNT = 50;

    /** Default constructor for the Forest Keeper Class.
     *
     */
    public ForestKeeper() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
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
    }

    @Override
    protected void addBehaviours() {
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new HealingVial()), DEFAULT_HEAL_VIAL_DROP_RATE);
        this.droppableItems.put(new DropAction(new Runes(DEFAULT_RUNE_DROP_AMOUNT)), DEFAULT_RUNES_DROP_RATE);
    }

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
            this.behaviours.put(2, new FollowBehaviour(otherActor));
        }
        return actions;
    }

    @Override
    public void sunnyWeather() {

    }

    @Override
    public void rainyWeather() {

    }
}
