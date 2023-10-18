package game.artifacts;

public interface LimitedUpgrade extends Upgradable {
    int getUpgradeLimit();
    boolean canUpgrade();
}
