package game.artifacts;

public interface Upgradable {
    void upgrade();
    int getUpgradeLimit();
    boolean canUpgrade();
}
