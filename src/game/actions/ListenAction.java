package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.friendly.merchants.Listenable;

import java.util.List;
import java.util.Random;

public class ListenAction extends Action {
    private final Listenable merchant;

    public ListenAction(Listenable merchant){
        this.merchant = merchant;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<String> monologueList = this.merchant.getMonologueList(actor);

        Random rand = new Random();
        return monologueList.get(rand.nextInt(monologueList.size()));
    }

    @Override
    public String menuDescription(Actor actor) {
        // TODO: ADD THIS
        return null;
    }
}
