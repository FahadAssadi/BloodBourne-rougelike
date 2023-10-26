package game.artifacts;

public interface Upgradable {
    void upgrade();
    boolean isUpgradable();
    int getUpgradePrice();
}
