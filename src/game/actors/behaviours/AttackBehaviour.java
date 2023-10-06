package game.actors.behaviours;

import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.capabilities.Status;

/**
 * A behavior class representing the behavior of attacking the player if they are adjacent to the actor.
 */
public class AttackBehaviour implements Behaviour {
    /**
     * Get the action to perform for attacking the player if they are in an adjacent location.
     *
     * @param engine The actor performing the behavior.
     * @param map   The game map in which the actor is located.
     * @return An AttackAction if the player is adjacent, or null if not.
     */
    @Override
    public Action getAction(Actor engine, GameMap map) {
        for (Exit exit : map.locationOf(engine).getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor actor = destination.getActor();

                if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    return new AttackAction(actor, map.locationOf(actor).toString());
                }

            }
        }
        return null;
    }
}
