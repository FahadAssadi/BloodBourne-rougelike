package game.monologue;

public interface MonologueListenable {

    Boolean hasDefeatedAbxervyer();
    Boolean hasGreatKnife();
    Boolean hasGiantHammer();

    default void registerAsMonologueListenable() {
        MonologueManager.getMonologueManager().addListener(this);
    }
}
