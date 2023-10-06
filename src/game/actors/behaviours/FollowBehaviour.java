package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * closer to a target Actor.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Debashish Sahoo
 * @author Fahad Assadi
 */

public class FollowBehaviour implements Behaviour {
	private final Actor target;

	/**
	 * Constructs a FollowBehaviour with a specified target actor.
	 *
	 * @param target The target actor that this behavior will try to follow.
	 */
	public FollowBehaviour(Actor target) {
		this.target = target;
	}

	/**
	 * Gets the action that the actor should take to follow the target actor.
	 *
	 * @param actor The actor that exhibits this behavior.
	 * @param map   The game map on which the actor resides.
	 * @return An Action object representing the movement towards the target actor,
	 *         or null if no suitable action is found.
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		if(!map.contains(target) || !map.contains(actor))
			return null;

		Location here = map.locationOf(actor);
		Location there = map.locationOf(target);

		int currentDistance = distance(here, there);
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.canActorEnter(actor)) {
				int newDistance = distance(destination, there);
				if (newDistance < currentDistance) {
					return new MoveActorAction(destination, exit.getName());
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