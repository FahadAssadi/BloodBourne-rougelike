package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friendly.merchants.Listenable;

import java.util.List;
import java.util.Random;


/**
 * A class representing an action to listen to a Listenable entity in a game.
 * Inherits from Action.
 */
public class ListenAction extends Action {
    private final Listenable merchant;


    /**
     * Constructs a ListenAction for the given Listenable entity.
     *
     * @param merchant The Listenable entity to listen to.
     */
    public ListenAction(Listenable merchant){
        this.merchant = merchant;
    }

    /**
     * Executes the action, retrieving a random monologue from the Listenable entity.
     *
     * @param actor The actor performing the action.
     * @param map   The game map.
     * @return A random monologue from the Listenable entity.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        List<String> monologueList = this.merchant.getMonologueList(actor);

        Random rand = new Random();
        return monologueList.get(rand.nextInt(monologueList.size()));
    }

    /**
     * Returns a menu description for the ListenAction.
     *
     * @param actor The actor performing the action.
     * @return A menu description for listening to the Listenable entity.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Listen to the " + this.merchant;
    }
}
