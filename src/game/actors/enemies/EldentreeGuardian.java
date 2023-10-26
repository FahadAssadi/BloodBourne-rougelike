package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.FollowBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.artifacts.consumables.HealingVial;
import game.artifacts.consumables.RefreshingFlask;
import game.artifacts.consumables.Runes;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * A class that represents a special type of enemy called "Eldentree Guardian".
 *
 * The Eldentree Guardian class extends the Enemy class and represents a unique enemy in the game.
 * It defines its behaviors, droppable items, and special characteristics on Void tiles.
 *
 * Created by: Kevin Chen
 */
public class EldentreeGuardian extends Enemy {
    private static final String DEFAULT_NAME = "Eldentree Guardian";
    private static final char DEFAULT_DISPLAY_CHAR = 'e';
    private static final int DEFAULT_HITPOINTS = 250;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 50;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 80;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "knocks";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 25;
    private static final int DEFAULT_REFRESHING_VIAL_DROP_RATE = 15;
    private static final int DEFAULT_RUNE_DROP_AMOUNT = 250;
    private static final int DEFAULT_ATTACK_BEHAVIOUR_PRIORITY = 1;
    private static final int DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY = 2;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 999;

    /**
     * Default constructor for the Eldentree Guardian Class.
     */
    public EldentreeGuardian() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Ability.VOID_PROOF);
    }

    /**
     * Custom constructor for the Eldentree Guardian Class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public EldentreeGuardian(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.VOID_PROOF);
    }

    /**
     * Adds the AttackBehaviour and WanderBehaviour to the behaviors map.
     */
    @Override
    protected void addBehaviours() {
        this.behaviours.put(DEFAULT_ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour());
        this.behaviours.put(DEFAULT_WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());;
    }

    /**
     * Adds droppable items (Healing Vial and Runes) to the droppableItems map with their drop rates.
     */
    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new HealingVial()), DEFAULT_HEAL_VIAL_DROP_RATE);
        this.droppableItems.put(new DropAction(new RefreshingFlask()), DEFAULT_REFRESHING_VIAL_DROP_RATE);
        this.droppableItems.put(new DropAction(new Runes(DEFAULT_RUNE_DROP_AMOUNT)), DEFAULT_RUNES_DROP_RATE);
    }

    /**
     * Returns the intrinsic weapon for the Eldentree Guardian.
     *
     * @return An IntrinsicWeapon representing the Eldentree Guardian's attack.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }

    /**
     * Define allowable actions for the Eldentree Guardian based on the presence of hostile actors.
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
}
