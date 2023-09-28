package game.artifacts.weapons.skills;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.capabilities.Status;

public class StabAndStepSkill extends Skill{
    private static final double DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE = 25;
    private final Actor otherActor;

    public StabAndStepSkill(WeaponItem weaponItem, Actor otherActor) {
        super(weaponItem, DEFAULT_STAMINA_CONSUMPTION_PERCENTAGE);
        this.otherActor = otherActor;
    }

    @Override
    public String ProcessWeaponSkill(Actor actor, GameMap map) {
        this.weaponItem.addCapability(Status.SKILL_ACTIVE);

        // Consume stamina from the actor
        this.ConsumeStamina(actor);

        // Attack the enemy
        new AttackAction(otherActor, map.locationOf(otherActor).toString(), this.getWeaponItem()).execute(actor, map);

        // Step away to safety
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(actor)) {
                new MoveActorAction(destination, destination.toString()).execute(actor, map);
                break;
            }

        }

        // TODO: ADD THE MESSAGE
        return null;
    }
}
