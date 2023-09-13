package game.positions.spawners;

public class Hut extends EnemyNest{
    // Default display character for the Hut ground
    private final static char DEFAULT_DISPLAY_CHAR = 'h';

    public Hut(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
    }
}
