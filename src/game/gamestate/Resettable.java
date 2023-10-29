package game.gamestate;

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
