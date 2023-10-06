package game.weather.states;

import game.weather.susceptibles.WeatherSusceptiblesManager;

/**
 * The `WeatherState` interface defines the contract for weather states in the game.
 * Classes implementing this interface must provide methods to process weather effects on
 * Weathersusceptible objects (actors and grounds) and convert the state to a human-readable string.
 */

public interface WeatherState {
    /**
     * Processes the current weather state's effects on WeatherSusceptible objects managed
     * by the `WeatherSusceptiblesManager`.
     *
     * @param weatherSusceptiblesManager The manager for WeatherSusceptibles.
     */
    void processWeatherState(WeatherSusceptiblesManager weatherSusceptiblesManager);

    /**
     * Converts the weather state to a human-readable string representation.
     *
     * @return A string describing the weather state.
     */
    String toString();
}
