package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.behaviours.AttackBehaviour;
import game.artifacts.consumables.Bloodberry;
import game.artifacts.consumables.Runes;
import game.capabilities.Status;

/**
 * A specific enemy type representing a LivingBranch in the game.
 *
 * The LivingBranch class extends the Enemy class and represents a type of enemy in the game.
 * It defines its behaviors, droppable items, and default attributes.
 *
 * Created By: Ishan Ingolikar
 * Modified By: Kevin Chen
 *
 */
public class LivingBranch extends Enemy {

    // Default attributes for the Living Branch
    private static final String DEFAULT_NAME = "Living Branch";
    private static final char DEFAULT_DISPLAY_CHAR = '?';
    private static final int DEFAULT_HITPOINTS = 75;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 250;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 90;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "knocks";
    private static final int DEFAULT_BLOOD_BERRY_DROP_RATE = 50;
    private static final int DEFAULT_RUNE_DROP_AMOUNT = 500;
    private static final int DEFAULT_ATTACK_BEHAVIOUR_PRIORITY = 1;


    /**
     * Default constructor for the LivingBranch Class.
     */
    public LivingBranch() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Status.VOID_PROOF);
    }

    /**
     * Constructor for the Enemy class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public LivingBranch(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.VOID_PROOF);
    }

    /**
     * Adds the AttackBehaviour to the behaviors map.
     */
    @Override
    protected void addBehaviours() {
        this.behaviours.put(DEFAULT_ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour());
    }

    /**
     * Adds droppable items (Bloodberry and Runes) to the droppableItems map with their drop rates.
     */
    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new Bloodberry()), DEFAULT_BLOOD_BERRY_DROP_RATE);
        this.droppableItems.put(new DropAction(new Runes(DEFAULT_RUNE_DROP_AMOUNT)), DEFAULT_RUNES_DROP_RATE);
    }

    /**
     * Returns the intrinsic weapon for the Living Branch.
     *
     * @return An IntrinsicWeapon representing the Living Branch's attack.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }

    /**
     * Define allowable actions for the Living Branch based on the presence of hostile actors.
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
