package game.positions.EnemyNest.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.misc.Utility;

public class RedWolfSpawner implements Spawner{
    private static final int SPAWN_CHANCE = 15;

    @Override
    public Actor spawnActor() {
        return null;
    }

    @Override
    public boolean doesSpawn() {
        return Utility.getRandomEventOccurs(SPAWN_CHANCE);
    }
}
