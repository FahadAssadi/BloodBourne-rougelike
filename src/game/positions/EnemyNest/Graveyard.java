package game.positions.EnemyNest;

import game.positions.EnemyNest.spawners.Spawner;

/**
 * A special type of ground representing a graveyard.
 * This ground can spawn actors from a Spawner and restricts actor movement.
 * Created By:
 * @author Fahad Assadi
 */
public class Graveyard extends EnemyNest {
    // Default display character for the Graveyard ground
    private static final char DEFAULT_DISPLAY_CHAR = 'n';

    /**
     * Constructor for the Graveyard ground.
     *
     * @param spawner The Spawner responsible for generating actors in the graveyard.
     */
    public Graveyard(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
    }
}
