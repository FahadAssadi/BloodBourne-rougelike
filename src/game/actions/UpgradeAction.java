package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.Upgradable;

public class UpgradeAction extends Action {
    /**
     * The item to be upgraded.
     */
    private final Upgradable upgradable;

    public UpgradeAction(Upgradable upgradable) {
        this.upgradable = upgradable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() < this.upgradable.getUpgradePrice()){
            return actor + " needs " + (this.upgradable.getUpgradePrice() - actor.getBalance()) + " more to complete the upgrade.";
        }

        actor.deductBalance(this.upgradable.getUpgradePrice());

        this.upgradable.upgrade();

        return actor + " upgrades " + this.upgradable + " for " + this.upgradable.getUpgradePrice();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades " + this.upgradable + " for " + this.upgradable.getUpgradePrice();
    }
}
