package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.capabilities.Status;
import game.artifacts.weapons.WeaponSkill;

/**
 * An action representing the activation of a focus skill associated with a weapon.
 * This action increases the weapon's damage multiplier, updates its hit rate, and consumes stamina.
 * Created by:
 * @author Fahad Assadi
 */
public class FocusAction extends Action {
    // Attributes for FocusAction
    private final WeaponItem weapon;
    private final WeaponSkill weaponSkill;
    private final float increaseDamageMultiplierBy;
    private final int updateHitRateBy;
    private final double staminaDecreasePercentage;
    private static final int DEFAULT_SKILL_DURATION = 5;

    /**
     * Constructor for the FocusAction.
     *
     * @param weapon                   The weapon with the focus skill
     * @param weaponSkill              The associated weapon skill
     * @param increaseDamageMultiplierBy Amount to increase the weapon's damage multiplier
     * @param updateHitRateBy          Amount to update the weapon's hit rate
     * @param staminaDecreasePercentage Percentage of stamina to consume
     */
    public FocusAction(WeaponItem weapon, WeaponSkill weaponSkill, float increaseDamageMultiplierBy, int updateHitRateBy, double staminaDecreasePercentage){
        this.weapon = weapon;
        this.weaponSkill = weaponSkill;
        this.increaseDamageMultiplierBy = increaseDamageMultiplierBy;
        this.updateHitRateBy = updateHitRateBy;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    /**
     * Execute the focus action, activating the weapon's focus skill.
     * This method increases the damage multiplier, updates the hit rate, and consumes stamina.
     *
     * @param actor The actor performing the action
     * @param map   The GameMap where the action is executed
     * @return A description of the action's outcome
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Activate the focus skill for the weapon
        this.weapon.addCapability(Status.SKILL_ACTIVE);
        this.weaponSkill.setSkillDuration(DEFAULT_SKILL_DURATION);

        // Increase the weapon's damage multiplier and update hit rate
        this.weapon.increaseDamageMultiplier(this.increaseDamageMultiplierBy);
        this.weapon.updateHitRate(this.updateHitRateBy);

        // Consume stamina from the actor
        int decreaseStaminaBy = (int) Math.round(actor.getAttribute(BaseActorAttributes.STAMINA) * this.staminaDecreasePercentage/100);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, decreaseStaminaBy);

        return actor + " takes a deep breath and focuses all their might!";
    }

    /**
     * Get the description of the action for display in the game menu.
     *
     * @param actor The actor performing the action
     * @return A description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the focus skill of " + this.weapon;
    }
}
