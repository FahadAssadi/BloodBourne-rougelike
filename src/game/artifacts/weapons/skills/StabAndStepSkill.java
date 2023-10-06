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
 * Class that represents the StabAndStepSkill for a weapon
 */
public class StabAndStepSkill extends Skill{
    private static final double DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE = 25;

    /**
     * Constructor for the Stab and Step Skill
     *
     * @param weaponItem The weapon item to apply the skill to
     * @param otherActor The other actor to target the skill at
     */
    public StabAndStepSkill(WeaponItem weaponItem, Actor otherActor) {
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

        String message;

        // Attack the enemy
        message = new AttackAction(this.getTargetActor(), map.locationOf(this.getTargetActor()).toString(), this.getWeaponItem()).execute(actor, map);

        // Step away to safety
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(actor)) {
                message += "\n" + new MoveActorAction(destination, destination.toString()).execute(actor, map);
                break;
            }
        }

        return message;
    }
}
