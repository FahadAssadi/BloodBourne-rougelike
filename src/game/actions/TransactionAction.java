package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.capabilities.TransactionType;

public class TransactionAction extends Action {

    private Item item;
    private TransactionType type;
    private Actor buyer;
    private Actor seller;
    private double price;

    public TransactionAction(Item item, TransactionType type, Actor buyer, Actor seller, double price) {
        this.item = item;
        this.type = type;
        this.buyer = buyer;
        this.seller = seller;
        this.price = price;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // TO-DO

        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        if (type == TransactionType.PURCHASE) {
            return actor + "purchases " + item + " for " + price;
        } else {
            return actor + "sells " + item + " for " + price;
        }
    }
}
