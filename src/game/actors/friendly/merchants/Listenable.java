package game.actors.friendly.merchants;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.List;

/**
 * An interface for classes that speak and monologue like Blacksmith.
 */
public interface Listenable {

    /**
     * Retrieves a list of monologues based on the specified actor's inventory and statuses.
     *
     * @param actor The player, to check if he has defeated the boss, has giant hammer and so on.
     * @return A list of strings representing monologues for the merchant/friendly npc.
     */
    List<String> getMonologueList(Actor actor);
}
