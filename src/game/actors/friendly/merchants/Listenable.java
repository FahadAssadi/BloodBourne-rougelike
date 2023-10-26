package game.actors.friendly.merchants;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.List;

public interface Listenable {
    List<String> getMonologueList(Actor actor);
}
