package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.Upgradable;

/**
 * A class representing an action to upgrade an upgradable object in a game.
 * inherits from Action.
 */
public class UpgradeAction extends Action {
    private final Upgradable upgradable;

    /**
     * Constructs an UpgradeAction for the given upgradable object.
     *
     * @param upgradable The upgradable object to be upgraded.
     */
    public UpgradeAction(Upgradable upgradable) {
        this.upgradable = upgradable;
    }

    /**
     * Executes the upgrade action, deducts the cost, and upgrades the upgradable object.
     *
     * @param actor The actor performing the upgrade.
     * @param map   The game map.
     * @return A message describing the outcome of the upgrade action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() < this.upgradable.getUpgradePrice()){
            return actor + " needs " + (this.upgradable.getUpgradePrice() - actor.getBalance()) + " more to complete the upgrade.";
        }

        actor.deductBalance(this.upgradable.getUpgradePrice());

        this.upgradable.upgrade();

        return actor + " upgrades " + this.upgradable + " for " + this.upgradable.getUpgradePrice();
    }

    /**
     * Returns a menu description for the upgrade action.
     *
     * @param actor The actor performing the upgrade.
     * @return A menu description for the upgrade action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " upgrades " + this.upgradable + " for " + this.upgradable.getUpgradePrice();
    }
}
