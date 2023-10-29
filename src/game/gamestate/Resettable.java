package game.gamestate;

/**
 * An interface for classes that can be reset when the game essentially restarts.
 */
public interface Resettable {
    /**
     * Reset logic for the resettable entity
     */
    void reset();

    /**
     * Register entity to the EntityManager
     */
    default void registerResettable() {
        EntityManager.getEntityManager().registerResettable(this);
    }
}
