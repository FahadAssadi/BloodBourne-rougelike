package game.weather;

import edu.monash.fit2099.engine.displays.Display;
import game.weather.states.WeatherState;
import game.weather.states.RainyState;
import game.weather.states.SunnyState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weather {

    private static Weather weather;
    private WeatherState weatherState;

    private int currentWeatherIndex = 0;


    private Weather(){

        this.weatherTransition();
    }



    public static Weather getWeather() {
        if (weather == null){
            weather = new Weather();
        }

        return weather;
    }

    public WeatherState getWeatherState(){
        return weather.weatherState;
    }

    public void weatherTransition(){
        ArrayList<WeatherState> weatherStateList = new ArrayList<>(Arrays.asList(
                new SunnyState(),
                new RainyState()
        ));
        this.currentWeatherIndex = (this.currentWeatherIndex + 1) % weatherStateList.size();

        this.weatherState = weatherStateList.get(this.currentWeatherIndex);

        new Display().println( "The weather is now " + this.weatherState.toString() + "...");
    }
}
