package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.Upgradable;

public class UpgradeAction extends Action {
    private Upgradable item;

    public UpgradeAction(Upgradable item){
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        this.item.upgrade();
        // TODO: ADD THE MESSAGE
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        // TODO: ADD THE MESSAGE
        return null;
    }
}
