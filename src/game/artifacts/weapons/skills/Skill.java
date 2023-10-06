package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;


/**
 * Abstract class that represents a Skill for a weapon
 */
public abstract class Skill {
    protected final WeaponItem weaponItem;
    protected final double staminaDecreasePercentage;
    protected final Actor targetActor;

    /**
     * Constructor for the Skill
     * @param weaponItem The weapon item to apply the skill to
     * @param targetActor The target actor while using the skill
     * @param staminaDecreasePercentage Decrease in stamina when using the skill
     */
    public Skill(WeaponItem weaponItem, Actor targetActor, double staminaDecreasePercentage){
        this.weaponItem = weaponItem;
        this.targetActor = targetActor;
        this.staminaDecreasePercentage = staminaDecreasePercentage;
    }

    /**
     * Getter for the Skill's weapon item
     *
     * @return The weapon item
     */
    public WeaponItem getWeaponItem(){
        return this.weaponItem;
    }

    /**
     * Getter for the Skill's target actor
     *
     * @return The target Actor
     */
    public Actor getTargetActor(){
        return this.targetActor;
    }

    /**
     * Process and execute the effects of the Skill
     *
     * @param actor The Actor using the skill
     * @param map The game map
     * @return String representing the execution of the skill
     */
    public abstract String processWeaponSkill(Actor actor, GameMap map);

    /**
     * Decreases the stamina of the actor using the Skill as a side effect
     *
     * @param actor The Actor using the skill
     * @return Boolean indicating whether the actor has sufficient stamina to being with
     */
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
