package game.weather;

public class Weather {

    private static Weather weather;
    private final WeatherTypes weatherType;
    private Weather(WeatherTypes weatherType) {
        this.weatherType = weatherType;
    }

    public static WeatherTypes getWeather() {
        if (weather == null) {
            weather = new Weather(WeatherTypes.SUNNY);
        }
        return weather.weatherType;
    }
}
