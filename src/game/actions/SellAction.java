package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class SellAction extends Action {

    private Item item;

    public SellAction(Item item){
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // EXECUTION TO-DO
        return actor + "sells " + item;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "sells " + item;
    }
}
