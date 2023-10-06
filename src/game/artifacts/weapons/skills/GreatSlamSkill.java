package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.capabilities.Status;

/**
 * Class that represents the GreatSlamSkill for a weapon
 */
public class GreatSlamSkill extends Skill {
    private static final double DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE = 5;
    private static final double DEFAULT_SPLASH_DAMAGE_PERCENTAGE = 0.5;
    private static final float SPLASH_DAMAGE_MULTIPLIER = 0.5F;
    private static final float DEFAULT_SPLASH_DAMAGE_MULTIPLIER = 1.0F;

    /**
     * Constructor for the GreatSlam Skill
     *
     * @param weaponItem The weapon item to apply the skill to
     * @param otherActor The other actor to target the skill at
     */
    public GreatSlamSkill(WeaponItem weaponItem, Actor otherActor) {
        super(weaponItem, otherActor, DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE);
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

        // Return if the actor doesn't have sufficient stamina
        if (!sufficientStamina){
            return actor + " has insufficient stamina.";
        }

        // Adding the Skill Active Status.
        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        // Calculating the splash damage
        int splashDamage = (int) (DEFAULT_SPLASH_DAMAGE_PERCENTAGE * getWeaponItem().damage());

        String message;

        Location enemyLocation = map.locationOf(getTargetActor());

        // Attack the enemy
        message = new AttackAction(this.getTargetActor(), enemyLocation.toString(), this.getWeaponItem()).execute(actor, map);

        // Step away to safety
        for (Exit exit : enemyLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                this.getWeaponItem().updateDamageMultiplier(SPLASH_DAMAGE_MULTIPLIER);
                new AttackAction(destination.getActor(),destination.toString(),this.getWeaponItem()).execute(actor, map);

                this.getWeaponItem().updateDamageMultiplier(DEFAULT_SPLASH_DAMAGE_MULTIPLIER);
            }
        }

        message += "\n" + "SLAMMED everyone for " + splashDamage + " damage";

        return message;
    }
}

