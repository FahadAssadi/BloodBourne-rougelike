package game.artifacts.consumables;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface for consumable items in the game.
 */
public interface Consumable {
    /**
     * Define the behavior of consuming the item by an actor.
     *
     * @param actor The actor consuming the item.
     */
    void consume(Actor actor);
}
