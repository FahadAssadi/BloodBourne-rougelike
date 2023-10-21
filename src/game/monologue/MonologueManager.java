package game.monologue;

import java.util.ArrayList;
import java.util.List;

public class MonologueManager {

    private static MonologueManager monologueManager;
    private MonologueListenable listener;

    /**
     * Constructor for `MonologueManager`.
     *
     */
    private MonologueManager(){
    }

    /**
     * Retrieves the singleton instance of the {@code MonologueManager}. If an instance does not
     * exist, it creates a new one.
     *
     * @return The singleton instance of the {@code MonologueManager}.
     */
    public static MonologueManager getMonologueManager() {
        if (monologueManager == null){
            monologueManager = new MonologueManager();
        }

        return monologueManager;
    }

    /**
     * Adds a {@code MonologueListenable} entity to the list of entities that can listen to monologues.
     *
     * @param monologueListenable The {@code MonologueListenable} entity to be added.
     */
    public void addListener(MonologueListenable monologueListenable){
        monologueManager.listener = monologueListenable;
    }

    public List<String> getMonologues(List<String> monologueOptions) {
        List<String> monologues = new ArrayList<>();

        // TODO

        return monologues;
    }

}
