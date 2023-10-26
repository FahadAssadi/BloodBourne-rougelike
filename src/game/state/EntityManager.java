package game.state;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private static EntityManager entityManager;

    private final List<Resettable> resettables;

    /**
     * Constructor for `EntityManager`.
     *
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
     * Adds a {@code Resettable} entity to the list of entities that can have their state reset.
     *
     * @param resettable The {@code Resettable} entity to be added.
     */
    public void addResettable(Resettable resettable){
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
