package game.gamestate;

public interface Resettable {
    void reset();

    default void registerResettable() {
        EntityManager.getEntityManager().registerResettable(this);
    }
}
