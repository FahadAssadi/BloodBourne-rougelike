package game.monologue;

public interface MonologueListenable {

    public Boolean hasDefeatedAbxervyer();
    public Boolean hasGreatKnife();

    default void registerAsMonologueListenable() {
        MonologueManager.getMonologueManager().addListener(this);
    }
}
