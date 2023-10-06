package game.weather;

import edu.monash.fit2099.engine.displays.Display;
import game.weather.states.WeatherState;
import game.weather.states.RainyState;
import game.weather.states.SunnyState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The `Weather` class represents the current weather state in the game and manages transitions between
 * different weather states. It follows the Singleton pattern to ensure a single instance throughout the game.
 * Weather states include sunny and rainy conditions.
 */
public class Weather {
    private static Weather weather;
    private WeatherState weatherState;
    private int currentWeatherIndex = 0;

    /**
     * Private constructor to create a new instance of `Weather` and initialize the initial weather state.
     * This constructor is called only once when the first instance is created.
     */
    private Weather(){
        this.weatherTransition();
    }

    /**
     * Returns a list of available weather states, including sunny and rainy states.
     *
     * @return An ArrayList of available weather states.
     */
    private ArrayList<WeatherState> returnWeatherStates(){

         return new ArrayList<>(Arrays.asList(
                 new SunnyState(),
                 new RainyState()
         ));
    }

    /**
     * Retrieves the singleton instance of the `Weather` class. If an instance does not exist,
     * it creates a new one.
     *
     * @return The singleton instance of the `Weather` class.
     */
    public static Weather getWeather() {
        if (weather == null){
            weather = new Weather();
        }

        return weather;
    }

    /**
     * Gets the current weather state.
     *
     * @return The current weather state.
     */
    public WeatherState getWeatherState(){
        return weather.weatherState;
    }


    /**
     * Transitions to the next weather state and updates the current weather state accordingly.
     * This method cycles through available weather states (sunny and rainy).
     */
    public void weatherTransition(){
        this.currentWeatherIndex = (this.currentWeatherIndex + 1) % returnWeatherStates().size();

        this.weatherState = returnWeatherStates().get(this.currentWeatherIndex);

        new Display().println( "The weather is now " + this.weatherState.toString() + "...");
    }
}
