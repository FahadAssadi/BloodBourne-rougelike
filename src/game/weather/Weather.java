package game.weather;

import game.weather.states.WeatherState;
import game.weather.states.RainyState;
import game.weather.states.SunnyState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Weather {
    private static Weather weather;
    private WeatherState weatherState;
    private List<WeatherState> weatherStateList;
    private int currentWeatherIndex = -1;

    private Weather(){
        this.createWeatherStates();
        this.weatherTransition();
    }

    private void createWeatherStates(){
        this.weatherStateList = new ArrayList<>(Arrays.asList(
                new SunnyState(),
                new RainyState()
        ));
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
        this.currentWeatherIndex = (this.currentWeatherIndex + 1) % this.weatherStateList.size();

        this.weatherState = this.weatherStateList.get(this.currentWeatherIndex);
    }
}
