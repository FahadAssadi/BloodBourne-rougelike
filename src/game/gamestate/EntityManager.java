package game.gamestate;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;
import game.artifacts.consumables.Runes;
import game.capabilities.Status;
import game.positions.LockedGate;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private static EntityManager entityManager;

    private List<Resettable> resettables;

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
     * Resets all resettables
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
