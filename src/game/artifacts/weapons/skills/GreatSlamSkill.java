package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.capabilities.Status;

public class GreatSlamSkill extends Skill {
    private static final double DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE = 5;
    private static  final float  SPLASH_DAMAGE_MULTIPLIER = 0.5F;
    private static  final float  DEFAULT_SPLASH_DAMAGE_MULTIPLIER = 1.0F;



    public GreatSlamSkill(WeaponItem weaponItem, Actor otherActor) {
        super(weaponItem, otherActor, DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE);
    }


    @Override
    public String processWeaponSkill(Actor actor, GameMap map) {
        // Consume stamina from the actor
        boolean sufficientStamina = this.consumeStamina(actor);

        if (!sufficientStamina){
            return actor + " has insufficient stamina.";
        }

        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        int splashDamage = (int) (0.5 * getWeaponItem().damage());

        String message;

        // Attack the enemy
        message = new AttackAction(this.getTargetActor(), map.locationOf(this.getTargetActor()).toString(), this.getWeaponItem()).execute(actor, map);

        // Step away to safety
        for (Exit exit : map.locationOf(getTargetActor()).getExits()) {
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

