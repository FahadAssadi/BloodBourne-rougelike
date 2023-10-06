package game.weather.susceptibles;
/**
 * The `RainySusceptible` interface defines the contract for entities that can be affected by
 * rainy weather conditions in the game.
 * Classes implementing this interface must provide a method to apply the relevant rainy weather
 * effect and can optionally register themselves as RainySusceptible entities.
 *
 * @see WeatherSusceptiblesManager
 */
public interface RainySusceptible{
    /**
     * Applies the relevant rainy weather effect to the RainySusceptible entity.
     *
     * @return A string representing the execution of the effect.
     */
    String rainyWeather();
    /**
     * Registers the implementing entity as a RainySusceptible entity with the
     * `WeatherSusceptiblesManager`. This method is optional.
     * Entities that want to be managed by the manager can call this method to register themselves.
     * The default implementation adds the entity to the manager, but it can be overridden if
     * additional registration logic is needed.
     *
     * @see WeatherSusceptiblesManager
     */
    default void registerAsRainySusceptible() {
        WeatherSusceptiblesManager.getWeatherSusceptiblesManager().addRainySusceptible(this);
    }
}
