package game.weather.states;

import game.weather.susceptibles.WeatherSusceptiblesManager;
/**
 * The `RainyState` class represents a weather state called "Rainy" in the game.
 * It implements the `WeatherState` interface and defines the specific behavior for
 * processing the "Rainy" weather state on RainySusceptibles.
 *
 * @see WeatherState
 * @see WeatherSusceptiblesManager
 */
public class RainyState implements WeatherState {
    /**
     * Processes the "Rainy" weather state's effects on RainySusceptibles managed
     * by the `WeatherSusceptiblesManager`.
     *
     * @param weatherSusceptiblesManager The manager for WeatherSusceptibles.
     */
    @Override
    public void processWeatherState(WeatherSusceptiblesManager weatherSusceptiblesManager) {
        weatherSusceptiblesManager.processAllRainySusceptible();
    }
    /**
     * Converts the "Rainy" weather state to a human-readable string representation.
     *
     * @return A string describing the "Rainy" weather state.
     */
    @Override
    public String toString() {
        return "rainy";
    }
}
