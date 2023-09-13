package game.weapons;

/**
 * Contract for objects that have weapon skills in your game.
 * Created By:
 * @author Fahad Assadi
 */
public interface WeaponSkill {
    /**
     * Process the weapon skill, usually during each game turn.
     *
     * @param isHeld Indicates if the weapon with this skill is currently held by an actor.
     */
    void processWeaponSkill(boolean isHeld);
    /**
     * Reset the weapon skill, typically after its effects have expired.
     */
    void resetWeapon();
    /**
     * Set the duration of the weapon skill.
     *
     * @param skillDuration Duration of the weapon skill.
     */
    void setSkillDuration(int skillDuration);
}
