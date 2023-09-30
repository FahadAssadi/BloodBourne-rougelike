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

    public GreatSlamSkill(WeaponItem weaponItem, Actor otherActor) {
        super(weaponItem, otherActor, DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE);
    }


    @Override
    public String processWeaponSkill(Actor actor, GameMap map) {
        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        // Consume stamina from the actor
        this.consumeStamina(actor);
        int splashDamage = (int) (0.5 * getWeaponItem().damage());


        String message;

        // Attack the enemy
        message = new AttackAction(this.getTargetActor(), map.locationOf(this.getTargetActor()).toString(), this.getWeaponItem()).execute(actor, map);

        // Step away to safety
        for (Exit exit : map.locationOf(getTargetActor()).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                destination.getActor().hurt(splashDamage);
            }
        }
        message += "\n" + "SLAMMED everyone for " + splashDamage + " damage";


        return message;
    }
}

