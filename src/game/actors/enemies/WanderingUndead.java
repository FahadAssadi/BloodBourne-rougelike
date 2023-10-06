package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.OldKey;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.Runes;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.capabilities.Status;

/**
 * A specific enemy type representing a Wandering Undead in the game.
 *
 * The WanderingUndead class extends the Enemy class and represents a specific enemy type in the game.
 * It defines its behaviors, droppable items, and default attributes.
 *
 * Created By:
 * @author Fahad Assadi
 * Modified by:
 * @author Fahad Assadi
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
    private static final int DEFAULT_RUNE_DROP_AMOUNT = 50;
    private static final int DEFAULT_ATTACK_BEHAVIOUR_PRIORITY = 1;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 999;

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
     * Adds the AttackBehaviour and WanderBehaviour to the behaviors map.
     */
    @Override
    protected void addBehaviours() {
        this.behaviours.put(DEFAULT_ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour());
        this.behaviours.put(DEFAULT_WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());
    }

    /**
     * Adds droppable items (Old Key, Healing Vial, and Runes) to the droppableItems map with their drop rates.
     */
    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new OldKey()), DEFAULT_OLD_KEY_DROP_RATE);
        this.droppableItems.put(new DropAction(new HealingVial()), DEFAULT_HEAL_VIAL_DROP_RATE);
        this.droppableItems.put(new DropAction(new Runes(DEFAULT_RUNE_DROP_AMOUNT)), DEFAULT_RUNES_DROP_RATE);
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
