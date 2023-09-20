package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class TransactionAction extends Action {

    private Item item;
    private Actor seller;
    private Actor buyer;

    public TransactionAction(Item item, Actor seller, Actor buyer) {
        this.item = item;
        this.seller = seller;
        this.buyer = buyer;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // TO-DO
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        // TO-DO
        return null;
    }
}
