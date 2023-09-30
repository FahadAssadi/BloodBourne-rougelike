package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.weapons.skills.Skill;

public class ActivateSkillAction extends Action {
    private final Skill skill;
    public ActivateSkillAction(Skill skill){
        this.skill = skill;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return this.skill.processWeaponSkill(actor, map);
    }

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
