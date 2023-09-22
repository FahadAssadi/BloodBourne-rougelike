package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.consumables.Consumable;

public class PurchaseAction extends Action {

    private Item item;

    public PurchaseAction(Item item){
        this.item = item;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // EXECUTION TO-DO
        return actor + "purchases " + item;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "purchases " + item;
    }
}
