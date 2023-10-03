package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public abstract class Skill {
    protected final WeaponItem weaponItem;
    protected final double staminaDecreasePercentage;
    protected final Actor targetActor;

    public Skill(WeaponItem weaponItem, Actor targetActor, double staminaDecreasePercentage){
        this.weaponItem = weaponItem;
        this.targetActor = targetActor;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    public WeaponItem getWeaponItem(){
        return this.weaponItem;
    }

    public Actor getTargetActor(){
        return this.targetActor;
    }

    public abstract String processWeaponSkill(Actor actor, GameMap map);

    protected boolean consumeStamina(Actor actor){
        // Consume stamina from the actor
        int decreaseStaminaBy = (int) Math.round(actor.getAttribute(BaseActorAttributes.STAMINA) * this.staminaDecreasePercentage/100);

        if (actor.getAttribute(BaseActorAttributes.STAMINA) > decreaseStaminaBy){
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, decreaseStaminaBy);
            return true;
        }

        return false;
    }

}
