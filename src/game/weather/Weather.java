package game.weather;

public class Weather {

    private static Weather weather;
    private final WeatherTypes weatherType;
    public static final WeatherTypes[] allWeatherTypes = WeatherTypes.values();
    private static int currentIndex = 0;

    private static boolean hasWeatherUpdated;

    private Weather(WeatherTypes weatherType) {
        this.weatherType = weatherType;
    }

    public static WeatherTypes getWeather() {
        if (weather == null) {
            weather = new Weather(WeatherTypes.SUNNY);
        }
        return weather.weatherType;
    }

    public static void setNextWeather() {
        currentIndex = (currentIndex + 1) % allWeatherTypes.length;
        Weather.weather = new Weather(allWeatherTypes[currentIndex]);
        hasWeatherUpdated = true;


    }

    public static boolean getHasWeatherUpdated() {
        return hasWeatherUpdated;
    }
    public static void setHasWeatherUpdated(boolean newBoolean) {
        hasWeatherUpdated = newBoolean;
    }
}
