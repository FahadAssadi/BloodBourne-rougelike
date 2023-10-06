package game.weather.susceptibles;

import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.List;
/**
 * The `WeatherSusceptiblesManager` class manages entities that can be affected by weather conditions
 * in the game. It provides functionality to add entities susceptible to Sunny or Rxainy weather and
 * to process the effects of those weather conditions on the registered entities.
 */
public class WeatherSusceptiblesManager {


    private static WeatherSusceptiblesManager weatherSusceptiblesManager;
    private final List<SunnySusceptible> sunnySusceptibles;
    private final List<RainySusceptible> rainySusceptibles;

    /**
     * Constructor for `WeatherSusceptiblesManager` that initializes lists of SunnySusceptibles and RainySusceptibles.
     *
     */
    public WeatherSusceptiblesManager(){
        this.sunnySusceptibles = new ArrayList<>();
        this.rainySusceptibles = new ArrayList<>();
    }

    /**
     * Retrieves the singleton instance of the {@code WeatherSusceptiblesManager}. If an instance does not
     * exist, it creates a new one.
     *
     * @return The singleton instance of the {@code WeatherSusceptiblesManager}.
     */
    public static WeatherSusceptiblesManager getWeatherSusceptiblesManager() {
        if (weatherSusceptiblesManager == null){
            weatherSusceptiblesManager = new WeatherSusceptiblesManager();
        }

        return weatherSusceptiblesManager;
    }

    /**
     * Adds a {@code SunnySusceptible} entity to the list of entities susceptible to sunny weather effects.
     *
     * @param sunnySusceptible The {@code SunnySusceptible} entity to be added.
     */
        public void addSunnySusceptible(SunnySusceptible sunnySusceptible){
        weatherSusceptiblesManager.sunnySusceptibles.add(sunnySusceptible);
    }

    /**
     * Adds a {@code RainySusceptible} entity to the list of entities susceptible to rainy weather effects.
     *
     * @param rainySusceptible The {@code RainySusceptible} entity to be added.
     */
    public void addRainySusceptible(RainySusceptible rainySusceptible){
        weatherSusceptiblesManager.rainySusceptibles.add(rainySusceptible);
    }

    /**
     * Processes all entities susceptible to sunny weather by invoking their `sunnyWeather` method.
     */
    public void processAllSunnySusceptible(){
        for (SunnySusceptible sunnySusceptible: weatherSusceptiblesManager.sunnySusceptibles) {
            new Display().println(sunnySusceptible.sunnyWeather());
        }
    }

    /**
     * Processes all entities susceptible to rainy weather by invoking their `rainyWeather` method.
     */
    public void processAllRainySusceptible(){
        for (RainySusceptible rainySusceptible: weatherSusceptiblesManager.rainySusceptibles) {
            new Display().println(rainySusceptible.rainyWeather());
        }
    }
}
