package game.weather.states;

import game.weather.susceptibles.WeatherSusceptiblesManager;

/**
 * The `SunnyState` class represents a weather state called "Sunny" in the game.
 * It implements the `WeatherState` interface and defines the specific behavior for
 * processing the "Sunny" weather state.
 *
 * @see WeatherState
 * @see WeatherSusceptiblesManager
 */
public class SunnyState implements WeatherState {
    /**
     * Processes the "Sunny" weather state's effects on SunnySusceptibles managed
     * by the `WeatherSusceptiblesManager`.
     *
     * @param weatherSusceptiblesManager The manager for WeatherSusceptibles.
     */
    @Override
    public void processWeatherState(WeatherSusceptiblesManager weatherSusceptiblesManager) {
        weatherSusceptiblesManager.processAllSunnySusceptible();
    }

    /**
     * Converts the "Sunny" weather state to a human-readable string representation.
     *
     * @return A string describing the "Sunny" weather state.
     */
    @Override
    public String toString() {
        return "sunny";
    }
}
