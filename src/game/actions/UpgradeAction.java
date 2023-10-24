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
        String message;

        this.upgradable.upgrade();

        if (actor.getBalance() < upgradable.getUpgradablePrice()){
            return actor + " needs " + (upgradable.getUpgradablePrice() - actor.getBalance()) + " more to complete the upgrade.";
        }

        actor.deductBalance(this.upgradable.getUpgradablePrice());

        message = actor + " upgrades " + this.upgradable + " for " + upgradable.getUpgradablePrice();

        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades " + upgradable + " for " + upgradable.getUpgradablePrice();
    }
}
