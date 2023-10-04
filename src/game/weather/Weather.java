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
    private List<WeatherState> weatherStateList;

    private static final List<SunnySusceptible> sunnySusceptibles = new ArrayList<>();
    private static final List<RainySusceptible> rainySusceptibles = new ArrayList<>();

    private int currentWeatherIndex = 0;

    public static List<SunnySusceptible> getSunnySusceptibles() {
        return sunnySusceptibles;
    }

    public static List<RainySusceptible> getRainySusceptibles() {
        return rainySusceptibles;
    }

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

    public String weatherTransition(){
        this.currentWeatherIndex = (this.currentWeatherIndex + 1) % this.weatherStateList.size();

        this.weatherState = this.weatherStateList.get(this.currentWeatherIndex);

        return "The weather is now " + this.weatherState.toString() + "...";
    }
}
