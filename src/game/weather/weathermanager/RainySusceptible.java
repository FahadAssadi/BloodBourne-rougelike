package game.weather.weathermanager;

public interface RainySusceptible{
    /**
     * Applies the relevant rainy weather effect to the susceptible entity
     *
     * @return String representing the execution of the effect
     */
    String rainyWeather();

    default void registerAsRainySusceptible() {
        WeatherSusceptiblesManager.getWeatherSusceptiblesManager().addRainySusceptible(this);
    }
}
