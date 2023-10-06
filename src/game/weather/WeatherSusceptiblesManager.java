package game.weather;

import edu.monash.fit2099.engine.displays.Display;

import java.util.ArrayList;
import java.util.List;

public class WeatherSusceptiblesManager {
    // To add a new weather you need to create a new list function to add the new to the new list
    // and a function to loop through the list and process all the objects in that list.

    // Lists for all different seasons and weathers
    private static final List<SunnySusceptible> sunnySusceptibles = new ArrayList<>();
    private static final List<RainySusceptible> rainySusceptibles = new ArrayList<>();

    // Adding to the lists
    public static void addSunnySusceptible(SunnySusceptible sunnySusceptible){
        sunnySusceptibles.add(sunnySusceptible);
    }

    public static void addRainySusceptible(RainySusceptible rainySusceptible){
        rainySusceptibles.add(rainySusceptible);
    }

    // Processing the lists
    public static void processAllSunnySusceptible(){
        for (SunnySusceptible sunnySusceptible: sunnySusceptibles) {
            new Display().println(sunnySusceptible.sunnyWeather());
        }
    }

    public static void processAllRainySusceptible(){
        for (RainySusceptible rainySusceptible: rainySusceptibles) {
            new Display().println(rainySusceptible.rainyWeather());
        }
    }
}
