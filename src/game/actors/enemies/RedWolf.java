package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.vials.HealingVial;
import game.capabilities.Status;
import game.misc.Utility;

public class RedWolf extends Enemy{
    private static final String DEFAULT_NAME = "Red Wolf";
    private static final char DEFAULT_DISPLAY_CHAR = 'r';
    private static final int DEFAULT_HITPOINTS = 25;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 15;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 85;

    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "bites";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 10;

    /**
     * Constructor for the RedWolf class.
     */
    public RedWolf() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
    }

    /**
     * Constructor for the Enemy class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public RedWolf(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Define the behavior when the Red Wolf becomes unconscious.
     * It may drop healing and refreshing vials based on random drop rates.
     *
     * @param actor The actor that caused the Red Wolf to become unconscious.
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
        }
        return actions;
    }
}
