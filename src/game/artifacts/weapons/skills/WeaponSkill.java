package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Interface for weapon items that have skills
 */
public interface WeaponSkill {

    /**
     * Returns the skill with the actor to apply the skill to
     *
     * @param actor The actor to apply the skill to
     * @return The Skill
     */
    Skill getSkill(Actor actor);

    /**
     * Reset the attributes of the weapon following the skill effects if needed
     */
    void resetWeapon();
}
