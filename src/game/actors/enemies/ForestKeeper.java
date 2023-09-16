package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.vials.HealingVial;
import game.artifacts.vials.RefreshingVial;
import game.capabilities.Status;
import game.misc.Utility;

/**
 * A class that represents a special type of enemy called "Forest Keeper"
 */
public class ForestKeeper extends Enemy{

    // Default attributes for the Forest Keeper
    private static final String DEFAULT_NAME = "Forest Keeper";
    private static final char DEFAULT_DISPLAY_CHAR = '8';
    private static final int DEFAULT_HITPOINTS = 125;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 25;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 75;

    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "knocks";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 20;

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

    /**
     * Define the behavior when the Forest Keeper becomes unconscious.
     * It may drop healing and refreshing vials based on random drop rates.
     *
     * @param actor The actor that caused the Forest Keeper to become unconscious.
     * @param map   The GameMap where the event occurred.
     * @return A string representing the result of becoming unconscious.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        boolean dropsHealVial = Utility.getRandomEventOccurs(DEFAULT_HEAL_VIAL_DROP_RATE);

        if (dropsHealVial) {
            new DropAction(new HealingVial()).execute(this, map);
        }

        return super.unconscious(actor, map);
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
        }
        return actions;
    }
}
