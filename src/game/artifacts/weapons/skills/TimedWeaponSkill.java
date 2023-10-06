package game.artifacts.weapons.skills;

/**
 * Interface for weapon items that have timed skills (last temporarily)
 */
public interface TimedWeaponSkill extends WeaponSkill {
    /**
     * Keeps track of the number of turns the weapon's skill can last for
     */
    void processSkillTimer();
}
