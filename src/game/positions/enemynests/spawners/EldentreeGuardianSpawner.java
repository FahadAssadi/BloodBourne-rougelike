package game.positions.enemynests.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import game.actors.enemies.EldentreeGuardian;

public class EldentreeGuardianSpawner extends Spawner{
    private static final int DEFAULT_SPAWN_CHANCE = 20;

    public EldentreeGuardianSpawner() {
        super(DEFAULT_SPAWN_CHANCE);
    }

    @Override
    public Actor spawnActor() {
        return new EldentreeGuardian();
    }
}
