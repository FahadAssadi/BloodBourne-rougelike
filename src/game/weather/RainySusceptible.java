package game.weather;

public interface RainySusceptible{
    String rainyWeather();

    default void registerAsRainySusceptible() {
        WeatherSusceptiblesManager.addRainySusceptible(this);
    }
}
