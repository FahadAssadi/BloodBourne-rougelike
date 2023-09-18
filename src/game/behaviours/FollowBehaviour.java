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

		Location here = map.locationOf(actor);
		Location there = null;

		// Find location of the player if the player is in the surroundings of the actor
		for (Exit exit : here.getExits()) {
			Location destination = exit.getDestination();
			if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {

				// Move to the player's old location if the player is in the surroundings of the actor
				there = destination;
				return new MoveActorAction(there, exit.getName());

			}
		}
		return null;
	}
}