package game.weather;



public interface RainySusceptible{
    String rainyWeather();


    default void registerAsRainySusceptible()
    {
        Weather.getRainySusceptibles().add(this);
    }

}
