package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.artifacts.weapons.skills.GreatSlamSkill;
import game.artifacts.weapons.skills.Skill;
import game.capabilities.Status;

public class GiantHammer extends WeaponItem {
    private static final String DEFAULT_NAME = "Giant hammer";
    private static final char DEFAULT_DISPLAY_CHAR = 'P';
    private static final int DEFAULT_DAMAGE = 160;
    private static final String DEFAULT_VERB = "smashes";
    private static final int DEFAULT_HITRATE = 90;
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    public GiantHammer() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_DAMAGE, DEFAULT_VERB, DEFAULT_HITRATE);
    }

    public GiantHammer(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    public Skill getSkill(Actor actor) {
        return new GreatSlamSkill(this, actor);
    }

    /**
     * Get a list of allowable actions for the Broadsword when it's in an unspecified location.
     *
     * @param actor The actor interacting with the Broadsword
     * @return ActionList containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor){
        ActionList actions = new ActionList();

        actions.add(new ActivateSkillAction(this.getSkill(actor)));

        return actions;
    }

    /**
     * Get a list of allowable actions for the Broadsword when it's in a specific location.
     *
     * @param otherActor The actor interacting with the Broadsword
     * @param location   The specific location of the Broadsword
     * @return ActionList containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();

        if (otherActor.hasCapability(Status.HOSTILE)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }

        return actions;
    }
}
