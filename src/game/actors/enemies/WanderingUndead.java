package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.OldKey;
import game.artifacts.vials.HealingVial;
import game.capabilities.Status;
import game.misc.Utility;

/**
 * A specific enemy type representing a Wandering Undead in the game.
 */
public class WanderingUndead extends Enemy {
    // Default attributes for the Wandering Undead
    private static final String DEFAULT_NAME = "Wandering Undead";
    private static final char DEFAULT_DISPLAY_CHAR = 't';
    private static final int DEFAULT_HITPOINTS = 100;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 30;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "whacks";
    private static final int DEFAULT_OLD_KEY_DROP_RATE = 25;
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 20;

    /**
     * Constructor for the WanderingUndead class.
     */
    public WanderingUndead() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
    }

    /**
     * Constructor for the WanderingUndead class with custom attributes.
     * @param name        name of the wandering undead
     * @param displayChar display char for the game's UI
     * @param hitPoints   the health of the wandering undead
     */
    public WanderingUndead(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * Define the intrinsic weapon for the Wandering Undead.
     *
     * @return An intrinsic weapon with specified damage and verb.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB);
    }

    /**
     * Define the behavior when the Wandering Undead becomes unconscious by an actor.
     * It may drop an old key and healing vials based on random drop rates.
     *
     * @param actor The actor that caused the Wandering Undead to become unconscious.
     * @param map   The GameMap where the event occurred.
     * @return A string representing the result of becoming unconscious.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        boolean dropOldKeyOccurs = Utility.getRandomEventOccurs(DEFAULT_OLD_KEY_DROP_RATE);

        if (dropOldKeyOccurs) {
            new DropAction(new OldKey()).execute(this,map);
        }

        boolean dropsHealVial = Utility.getRandomEventOccurs(DEFAULT_HEAL_VIAL_DROP_RATE);

        if (dropsHealVial) {
            new DropAction(new HealingVial()).execute(this, map);
        }

        return super.unconscious(actor, map);
    }

    /**
     * Define allowable actions for the Wandering Undead based on the presence of hostile actors.
     *
     * @param otherActor The other actor (usually the player) to check for hostility.
     * @param direction  The direction in which the action is allowed.
     * @param map        The GameMap where the action occurs.
     * @return A list of allowable actions for the Wandering Undead.
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
