package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public abstract class Skill {
    protected WeaponItem weaponItem;

    public Skill(WeaponItem weaponItem){
        this.weaponItem = weaponItem;
    }

    public WeaponItem getWeaponItem(){
        return this.weaponItem;
    }

    public abstract String ProcessWeaponSkill(Actor actor);

}
