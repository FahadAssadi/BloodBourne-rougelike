package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;

public class FocusSkill extends Skill{
    private static final double DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE = 20;
    private final float increaseDamageMultiplierBy;
    private final int updateHitRateBy;

    public FocusSkill(WeaponItem weaponItem, float increaseDamageMultiplierBy, int updateHitRateBy) {
        super(weaponItem, DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE);
        this.increaseDamageMultiplierBy = increaseDamageMultiplierBy;
        this.updateHitRateBy = updateHitRateBy;
    }

    @Override
    public String ProcessWeaponSkill(Actor actor) {
        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        // Consume stamina from the actor
        this.ConsumeStamina(actor);

        // Increase the weapon's damage multiplier and update hit rate
        this.weaponItem.increaseDamageMultiplier(this.increaseDamageMultiplierBy);
        this.weaponItem.updateHitRate(this.updateHitRateBy);

        return actor + " takes a deep breath and focuses all their might!";
    }
}
