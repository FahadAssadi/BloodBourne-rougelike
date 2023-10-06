package game.positions.EnemyNest;

import game.positions.EnemyNest.spawners.Spawner;
import game.weather.weathermanager.RainySusceptible;
import game.weather.weathermanager.SunnySusceptible;

/**
 * A special type of EnemyNest ground representing a Bush.
 * This ground can spawn actors from a Spawner and restricts actor movement.
 */
public class Bush extends EnemyNest implements SunnySusceptible, RainySusceptible {
    private static final char DEFAULT_DISPLAY_CHAR = 'm';

    /*
    Spawn chance when the weather is sunny
     */
    private static final int SUNNY_SPAWN_CHANCE = 30;

    /*
    Spawn chance when the weather is rainy
     */
    private static final int RAINY_SPAWN_CHANCE = 45;

    /**
     * Constructor for the Bush ground.
     *
     * @param spawner The Spawner responsible for generating actors in the Bush.
     */
    public Bush(Spawner spawner) {
        super(DEFAULT_DISPLAY_CHAR, spawner);

        // Register this entity as susceptible to rainy weather to the Weather Susceptibles Manager
        registerAsRainySusceptible();
        // Register this entity as susceptible to sunny weather to the Weather Susceptibles Manager
        registerAsSunnySusceptible();
    }


    /**
     * Applies the relevant sunny weather effect to the susceptible entity
     *
     * @return String representing the execution of the effect
     */
    @Override
    public String sunnyWeather() {
        spawner.setSpawnChance(SUNNY_SPAWN_CHANCE);
        return "The number of Red Wolves is starting to dwindle!";
    }

    /**
     * Applies the relevant rainy weather effect to the susceptible entity
     *
     * @return String representing the execution of the effect
     */
    @Override
    public String rainyWeather() {
        spawner.setSpawnChance(RAINY_SPAWN_CHANCE);
        return "The number of Red Wolves is starting to increase!";
    }
}
