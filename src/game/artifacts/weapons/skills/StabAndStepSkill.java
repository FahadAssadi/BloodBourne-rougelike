package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;

public class StabAndStepSkill extends Skill{
    private static final double DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE = 25;

    public StabAndStepSkill(WeaponItem weaponItem) {
        super(weaponItem, DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE);
    }

    @Override
    public String ProcessWeaponSkill(Actor actor) {
        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        // Consume stamina from the actor
        this.ConsumeStamina(actor);



        return null;
    }
}
