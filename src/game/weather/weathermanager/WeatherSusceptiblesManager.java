package game.weather.weathermanager;

import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.List;

public class WeatherSusceptiblesManager {
    // To add a new weather you need to create a new list function to add the new to the new list
    // and a function to loop through the list and process all the objects in that list.

    // Lists for all different seasons and weathers
    private static WeatherSusceptiblesManager weatherSusceptiblesManager;
    private final List<SunnySusceptible> sunnySusceptibles;
    private final List<RainySusceptible> rainySusceptibles;

    public WeatherSusceptiblesManager(){
        this.sunnySusceptibles = new ArrayList<>();
        this.rainySusceptibles = new ArrayList<>();
    }

    public static WeatherSusceptiblesManager getWeatherSusceptiblesManager() {
        if (weatherSusceptiblesManager == null){
            weatherSusceptiblesManager = new WeatherSusceptiblesManager();
        }

        return weatherSusceptiblesManager;
    }

    // Adding to the lists
    public void addSunnySusceptible(SunnySusceptible sunnySusceptible){
        weatherSusceptiblesManager.sunnySusceptibles.add(sunnySusceptible);
    }

    public void addRainySusceptible(RainySusceptible rainySusceptible){
        weatherSusceptiblesManager.rainySusceptibles.add(rainySusceptible);
    }

    // Processing the lists
    public void processAllSunnySusceptible(){
        for (SunnySusceptible sunnySusceptible: weatherSusceptiblesManager.sunnySusceptibles) {
            new Display().println(sunnySusceptible.sunnyWeather());
        }
    }

    public void processAllRainySusceptible(){
        for (RainySusceptible rainySusceptible: weatherSusceptiblesManager.rainySusceptibles) {
            new Display().println(rainySusceptible.rainyWeather());
        }
    }
}
