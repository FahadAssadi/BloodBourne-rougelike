package game.weather;

public interface WeatherSusceptible {


    void sunnyWeather();
    void rainyWeather();

    default boolean isCorrectWeather(WeatherTypes correctWeatherType)
    {
        return Weather.getWeather() == correctWeatherType;
    }
    default void forceWeatherChanges() {
        if (Weather.getHasWeatherUpdated()) {
            sunnyWeather();
            rainyWeather();
        }
    }

}
