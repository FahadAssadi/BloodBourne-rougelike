package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.artifacts.PurchasableItem;

public class PurchaseAction extends Action {

    private final PurchasableItem purchasableItem;

    public PurchaseAction(PurchasableItem purchasableItem){
        this.purchasableItem = purchasableItem;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // EXECUTION TO-DO
       purchasableItem.getTrickery().perform(actor,purchasableItem.getItem(), purchasableItem.getPrice());


        return actor + " purchased " + purchasableItem.getItem() + " for " + purchasableItem.getPrice();
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " purchases " + purchasableItem.getItem() + " for " + purchasableItem.getPrice();
    }
}
