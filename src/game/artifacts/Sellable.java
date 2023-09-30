package game.artifacts;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

public interface Sellable {
    ActionList allowableActions(Actor otherActor, Location location);
}
