package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;

/**
 * Class that represents the FocusSkill for a weapon
 */
public class FocusSkill extends Skill{
    private static final double DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE = 20;
    private final float increaseDamageMultiplierBy;
    private final int updateHitRateBy;

    /**
     * Constructor for the Focus Skill
     *
     * @param weaponItem The weapon item to apply the skill to
     * @param increaseDamageMultiplierBy Amount to increase damage multiplier of the weapon by
     * @param updateHitRateBy Amount to update the hit rate of the weapon by
     */
    public FocusSkill(WeaponItem weaponItem, float increaseDamageMultiplierBy, int updateHitRateBy) {
        super(weaponItem, null, DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE);
        this.increaseDamageMultiplierBy = increaseDamageMultiplierBy;
        this.updateHitRateBy = updateHitRateBy;
    }

    /**
     * Process and execute the effects of the Skill
     *
     * @param actor The Actor using the skill
     * @param map The game map
     * @return String representing the execution of the skill
     */
    @Override
    public String processWeaponSkill(Actor actor, GameMap map) {
        // Consume stamina from the actor
        boolean sufficientStamina = this.consumeStamina(actor);

        if (!sufficientStamina){
            return actor + " has insufficient stamina.";
        }

        // Adding the Skill Active Status.
        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        // Increase the weapon's damage multiplier and update hit rate
        this.weaponItem.increaseDamageMultiplier(this.increaseDamageMultiplierBy);
        this.weaponItem.updateHitRate(this.updateHitRateBy);

        return actor + " takes a deep breath and focuses all their might!";
    }
}
