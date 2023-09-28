package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public abstract class Skill {
    protected WeaponItem weaponItem;
    protected double staminaDecreasePercentage;

    public Skill(WeaponItem weaponItem, double staminaDecreasePercentage){
        this.weaponItem = weaponItem;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    public WeaponItem getWeaponItem(){
        return this.weaponItem;
    }

    public abstract String processWeaponSkill(Actor actor, GameMap map);

    protected void consumeStamina(Actor actor){
        // Consume stamina from the actor
        int decreaseStaminaBy = (int) Math.round(actor.getAttribute(BaseActorAttributes.STAMINA) * this.staminaDecreasePercentage/100);
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, decreaseStaminaBy);
    }

}
