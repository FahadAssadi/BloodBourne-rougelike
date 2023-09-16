package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Status;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Debashish Sahoo
 *
 */

public class FollowBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {

        if (!map.contains(actor)) {
            return null;
        }

        Location actorLocation = map.locationOf(actor);
        Location targetLocation = null;

        // Find location of the player if the player is in the surroundings of the actor
        for (Exit exit : actorLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                if (destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                    targetLocation = map.locationOf(destination.getActor());
                }
            }
        }

        // Move closer to the player if the player is in the surroundings of the actor
        if (targetLocation != null) {
            int currentDistance = distance(actorLocation, targetLocation);

            for (Exit exit : actorLocation.getExits()) {
                Location destination = exit.getDestination();

                if (destination.canActorEnter(actor)) {
                    int newDistance = distance(destination, targetLocation);
                    if (newDistance < currentDistance) {
                        return new MoveActorAction(destination, exit.getName());
                    }
                }
            }
        }

        return null;
    }

    /**
     * Compute the Manhattan distance between two locations.
     *
     * @param a the first location
     * @param b the first location
     * @return the number of steps between a and b if you only move in the four cardinal directions.
     */
    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}