package game.monologue;

public interface MonologueListenable {

    public Boolean hasDefeatedAbxervyer();
    public Boolean hasGreatKnife();
    public Boolean hasGiantHammer();

    default void registerAsMonologueListenable() {
        MonologueManager.getMonologueManager().addListener(this);
    }
}
