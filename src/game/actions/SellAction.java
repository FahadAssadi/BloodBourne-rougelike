package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class SellAction extends Action {

    private Item item;
    private int price;

    public SellAction(Item item, int price){
        this.item = item;
        this.price = price;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // TO-DO
        return actor + " sold " + item + " for " + price;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + item + " for " + price;
    }
}
