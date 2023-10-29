package game.gamestate;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that manages and triggers the resetting of Resettable game entities
 */
public class EntityManager {
    private static EntityManager entityManager;

    /**
     * Array of resettable game entities
     */
    private List<Resettable> resettables;

    /**
     * Constructor for `EntityManager`.
     */
    private EntityManager(){
        this.resettables = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of the {@code EntityManager}. If an instance does not
     * exist, it creates a new one.
     *
     * @return The singleton instance of the {@code EntityManager}.
     */
    public static EntityManager getEntityManager() {
        if (entityManager == null){
            entityManager = new EntityManager();
        }

        return entityManager;
    }

    /**
     * Resets all resettables
     * @param resettable is a list of all Resettable entities and grounds in the game.
     */
    public void registerResettable(Resettable resettable){
        entityManager.resettables.add(resettable);
    }

    /**
     * Resets the entities based on their reset() method implementation
     */
    public void resetEntities() {
        for (Resettable resettable: resettables) {
            resettable.reset();
        }
    }
}
