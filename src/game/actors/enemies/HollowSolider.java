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
 * A specific enemy type representing a Hollow Solider in the game.
 * Created By:
 * @author Fahad Assadi
 */
public class HollowSolider extends Enemy {
    // Default display character for the Void ground
    private static final String DEFAULT_NAME = "Hollow Solider";
    private static final char DEFAULT_DISPLAY_CHAR = '&';
    private static final int DEFAULT_HITPOINTS = 200;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 50;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "whacks";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 20;
    private static final int DEFAULT_REFRESHING_VIAL_DROP_RATE = 30;

    /**
     * Constructor for the HollowSolider class.
     */
    public HollowSolider(){
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
    }

    /**
     * Define the intrinsic weapon for the Hollow Solider.
     *
     * @return An intrinsic weapon with specified damage and verb.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB);
    }

    /**
     * Define the behavior when the Hollow Solider becomes unconscious.
     * It may drop healing and refreshing vials based on random drop rates.
     *
     * @param actor The actor that caused the Hollow Solider to become unconscious.
     * @param map   The GameMap where the event occurred.
     * @return A string representing the result of becoming unconscious.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        boolean dropsHealVial = Utility.getRandomEventOccurs(DEFAULT_HEAL_VIAL_DROP_RATE);

        if (dropsHealVial) {
            new DropAction(new HealingVial()).execute(this, map);
        }

        boolean dropsRefreshingVial = Utility.getRandomEventOccurs(DEFAULT_REFRESHING_VIAL_DROP_RATE);

        if (dropsRefreshingVial) {
            new DropAction(new RefreshingVial()).execute(this, map);
        }

        return super.unconscious(actor, map);
    }

    /**
     * Define allowable actions for the Hollow Solider based on the presence of hostile actors.
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
