package game.capabilities;

/**
 * Use this enum class to represent a status.
 * Example #1: if the player is sleeping, you can attack a Status.SLEEP to the player class
 * Created by:
 * @author Riordan D. Alfredo
 */
public enum Status {
    /**
     * This status indicates that an actor is hostile towards enemies or other actors.
     */
    HOSTILE_TO_ENEMY,
    /**
     * This status indicates that an actor is friendly towards the player.
     */
    FRIENDLY,
    /**
     * This status indicates that an actor is hostile towards the player.
     */
    HOSTILE,
    /**
     * This status represents the activation of a special skill or ability for an actor.
     */
    SKILL_ACTIVE,
    /**
     * This status indicates that an actor possesses a key.
     */
    POSSESS_KEY,
    /**
     * This status indicates that an actor carries a Great Knife
     */
    CARRIES_GREAT_KNIFE,
    /**
     * This status indicates that an actor carries a Giant Hammer
     */
    CARRIES_GIANT_HAMMER,
    /**
     * This status indicates that an actor has defeated the Forest Watcher
     */
    DEFEATED_ABXERVYER,
    /**
     * This status indicates that an entity is about to undergo a reset
     */
    RESET
}
