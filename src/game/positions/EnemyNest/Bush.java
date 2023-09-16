package game.positions.EnemyNest;

import game.positions.EnemyNest.spawners.Spawner;

public class Bush extends EnemyNest {
    private static final char DEFAULT_DISPLAY_CHAR = 'm';

    public Bush(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);
    }
}
