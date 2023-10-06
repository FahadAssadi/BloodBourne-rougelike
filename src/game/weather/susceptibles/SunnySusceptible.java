package game.weather.susceptibles;


/**
 * The `SunnySusceptible` interface defines the contract for entities that can be affected by
 * sunny weather conditions in the game.
 * Classes implementing this interface must provide a method to apply the relevant sunny weather
 * effect and can optionally register themselves as SunnySusceptible entities.
 *
 * @see WeatherSusceptiblesManager
 */
public interface SunnySusceptible{
    /**
     * Applies the relevant sunny weather effect to the susceptible entity.
     *
     * @return A string representing the execution of the effect.
     */
    String sunnyWeather();
    /**
     * Registers the implementing entity as a SunnySusceptible entity with the
     * `WeatherSusceptiblesManager`. This method is optional.
     * Entities that want to be managed by the manager can call this method to register themselves.
     * The default implementation adds the entity to the manager, but it can be overridden if
     * additional registration logic is needed.
     *
     * @see WeatherSusceptiblesManager
     */
    default void registerAsSunnySusceptible() {
        WeatherSusceptiblesManager.getWeatherSusceptiblesManager().addSunnySusceptible(this);
    }
}
