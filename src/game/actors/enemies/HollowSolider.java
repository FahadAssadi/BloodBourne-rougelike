package game.actors.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.vials.HealingVial;
import game.artifacts.vials.RefreshingFlask;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;

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

    public HollowSolider(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    @Override
    protected void addBehaviours() {
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(3, new WanderBehaviour());;
    }

    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new HealingVial()), DEFAULT_HEAL_VIAL_DROP_RATE);
        this.droppableItems.put(new DropAction(new RefreshingFlask()), DEFAULT_REFRESHING_VIAL_DROP_RATE);
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
