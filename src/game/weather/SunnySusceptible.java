package game.weather;

public interface SunnySusceptible{
    String sunnyWeather();


    default void registerAsSunnySusceptible()
    {
        Weather.getSunnySusceptibles().add(this);
    }

}
