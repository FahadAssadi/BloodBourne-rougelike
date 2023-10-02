package game.weather;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Weather {

    private static Weather weather;
    private final WeatherTypes weatherType;
    public static final WeatherTypes[] allWeatherTypes = WeatherTypes.values();
    private static int currentIndex = 0;


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


    }
}
