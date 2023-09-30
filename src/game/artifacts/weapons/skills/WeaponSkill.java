package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;

public interface WeaponSkill {
    Skill getSkill(Actor actor);
    void resetWeapon();
}
