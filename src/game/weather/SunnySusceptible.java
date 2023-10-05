package game.weather;

public interface SunnySusceptible{
    String sunnyWeather();


    default void registerAsSunnySusceptible()
    {
        WeatherSusceptiblesManager.getSunnySusceptibles().add(this);
    }

}
