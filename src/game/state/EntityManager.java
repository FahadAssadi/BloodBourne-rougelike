package game.state;

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

//    private final List<Resettable> resettables;
    private final List<Enemy> enemies;
    private final List<Enemy> bosses;
    private final List<Runes> runes;
    private final List<LockedGate> gates;

    private List<GameMap> maps;

    /**
     * Constructor for `EntityManager`.
     *
     */
    private EntityManager(){
        this.enemies = new ArrayList<>();
        this.bosses = new ArrayList<>();
        this.runes = new ArrayList<>();
        this.gates = new ArrayList<>();
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
     * Adds all game maps from the world to the manager
     */
    public void addGameMaps(List<GameMap> maps){
        entityManager.maps = maps;
    }

    /**
     * Resets all entities.
     */
    public void resetEntities() {
        resetEnemies();
        resetBosses();
        resetRunes();
        resetGates();
    }

    /**
     * Resets all spawned enemies (not incl. bosses) by removing them from the map
     */
    private void resetEnemies() {
        for (Actor enemy: enemies) {
            for (GameMap map: maps) {
                if (enemy.hasCapability(Status.HOSTILE) && !enemy.hasCapability(Status.BOSS)) {
                    map.removeActor(enemy);
                }
            }
        }
    }

    /**
     * Resets all bosses by resetting their health to full if they have not been defeated.
     */
    private void resetBosses() {
        // TODO
    }

    /**
     * Resets all unlocked gates by locking them again.
     */
    private void resetRunes() {
        // TODO
    }

    /**
     * Resets runes by wiping from the map all runes dropped in previous turns that the player did not pick up.
     */
    private void resetGates() {
        // TODO
    }



    // INITIAL APPROACH
//    public void addResettable(Resettable resettable){
//        entityManager.resettables.add(resettable);
//    }
//
//    /**
//     * Resets the entities based on their reset() method implementation
//     */
//    public void resetEntities() {
//        // TODO: This approach won't work for all entities as for removing enemies and runes, it must all be done here with access to the GameMaps
//        for (Resettable resettable: resettables) {
//            resettable.reset();
//        }
//    }

}
