package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.capabilities.Status;

/**
 * A behavior class representing the behavior of attacking the player if they are adjacent to the actor.
 */
public class AttackBehaviour implements Behaviour {

    private Actor target;


    /**
     * Retrieves the action to be performed by the wandering undead to attack a hostile actor if within range.
     *
     * This method checks for the presence of hostile actors in adjacent locations and returns an attack action
     * if a hostile actor is found within melee range. If the hostile actor is not within melee range, no action is returned.
     *
     * @param actor The wandering undead actor performing the action.
     * @param map   The game map containing the actor and other actors.
     * @return An attack action to be performed on a nearby hostile actor if present; otherwise, null.
     */

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        for (Exit exit: here.getExits())
        {
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY))
            {
                target  =  exit.getDestination().getActor();
            }
        }

        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location there = map.locationOf(target);

		/*
		<></>his if condition may seem redundant because if an actor is in one of the exits, it already warrants
		the distance between the WanderingUndead and Player being 1
		*/
        int currentDistance = distance(here, there);
        if(currentDistance <= 1)
        {

            return new AttackAction(target , there.toString());
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
