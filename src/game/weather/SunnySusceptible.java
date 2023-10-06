package game.weather;

public interface SunnySusceptible{
    /**
     * Applies the relevant sunny weather effect to the susceptible entity
     *
     * @return String representing the execution of the effect
     */
    String sunnyWeather();

    default void registerAsSunnySusceptible() {
        WeatherSusceptiblesManager.getWeatherSusceptiblesManager().addSunnySusceptible(this);
    }
}
