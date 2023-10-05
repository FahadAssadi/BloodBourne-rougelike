package game.weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherSusceptiblesManager {

    private static final List<SunnySusceptible> sunnySusceptibles = new ArrayList<>();
    private static final List<RainySusceptible> rainySusceptibles = new ArrayList<>();



    public static List<SunnySusceptible> getSunnySusceptibles() {
        return sunnySusceptibles;
    }

    public static List<RainySusceptible> getRainySusceptibles() {
        return rainySusceptibles;
    }
}
