package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;

public class FocusSkill extends Skill{
    private final float increaseDamageMultiplierBy;
    private final int updateHitRateBy;
    private final double staminaDecreasePercentage;

    public FocusSkill(WeaponItem weaponItem, float increaseDamageMultiplierBy, int updateHitRateBy, double staminaDecreasePercentage) {
        super(weaponItem);
        this.increaseDamageMultiplierBy = increaseDamageMultiplierBy;
        this.updateHitRateBy = updateHitRateBy;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    @Override
    public String ProcessWeaponSkill(Actor actor) {
        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        // Increase the weapon's damage multiplier and update hit rate
        this.weaponItem.increaseDamageMultiplier(this.increaseDamageMultiplierBy);
        this.weaponItem.updateHitRate(this.updateHitRateBy);

        // Consume stamina from the actor
        int decreaseStaminaBy = (int) Math.round(actor.getAttribute(BaseActorAttributes.STAMINA) * this.staminaDecreasePercentage/100);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, decreaseStaminaBy);

        return actor + " takes a deep breath and focuses all their might!";
    }
}
