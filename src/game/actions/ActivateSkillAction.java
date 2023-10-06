package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.weapons.skills.Skill;

/**
 * An action that allows an actor to attack another actor using their weapon's skill.
 */
public class ActivateSkillAction extends Action {
    /**
     * The Skill that is to be used in the attack
     */
    private final Skill skill;

    /**
     * Constructor.
     *
     * @param skill the Skill that is to be used in the attack
     */
    public ActivateSkillAction(Skill skill){
        this.skill = skill;
    }

    /**
     * Execute the action by processing the effects of the skill.
     *
     * @param actor The actor performing the action.
     * @param map   The GameMap where the action occurs.
     * @return A string describing the result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.skill.processWeaponSkill(actor, map);
    }

    /**
     * Get a menu description for the action.
     *
     * @param actor The actor performing the action.
     * @return A string describing the action for display in menus.
     */
    @Override
    public String menuDescription(Actor actor) {
        String message = actor + " activates the skill of " + this.skill.getWeaponItem();

        Actor targetActor = this.skill.getTargetActor();

        if (!(targetActor == null)){
            message += " on " + targetActor;
        }

        return message;
    }
}
