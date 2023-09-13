package game.misc;

import java.util.Random;

public class Utility {
    /**
     * Generate a random event based on the given probability.
     *
     * @param probability The probability of the event occurring (0.0 to 1.0).
     * @return true if the event occurs, false otherwise.
     */
    public static boolean getRandomEventOccurs(double probability){
        Random random = new Random();

        // Convert the given probability into a probability percentage/
        int probabilityPercentage = (int) (100 / probability);

        // Generate a random integer between 0 and the probability percentage
        // If the random integer is 0, the event occurs
        return random.nextInt(probabilityPercentage) == 0;
    }
}
