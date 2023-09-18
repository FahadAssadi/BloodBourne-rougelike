package game.actors.enemies;

import edu.monash.fit2099.demo.mars.behaviours.FollowBehaviour;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.capabilities.Status;

public class RedWolf extends Enemy{
    // Default attributes for the Red Wolf
    private static final String DEFAULT_NAME = "Red Wolf";
    private static final char DEFAULT_DISPLAY_CHAR = 'r';
    private static final int DEFAULT_HITPOINTS = 25;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 15;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 80;

    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "bites";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 10;

    /** Default constructor for the Red Wolf Class.
     *
     */
    public RedWolf() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.behaviours.put(2, new AttackBehaviour());
        this.behaviours.put(3, new WanderBehaviour());
    }
    /**
     * Constructor for the Red Wolf class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public RedWolf(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }



    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}
