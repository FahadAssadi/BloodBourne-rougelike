package game.actors.friendly.merchants;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ListenAction;
import game.actors.friendly.Friendly;
import game.capabilities.Ability;
import game.capabilities.Status;

import java.util.ArrayList;
import java.util.List;

public class Blacksmith extends Friendly implements Listenable{
    // Default attributes for the Blacksmith
    private static final String DEFAULT_NAME = "Blacksmith";
    private static final char DEFAULT_DISPLAY_CHAR = 'B';
    private static final int DEFAULT_HITPOINTS = 200; //randomly assigned, doesn't affect gameplay.

    public Blacksmith() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Ability.UPGRADES);
    }

    /**
     * The constructor of the Blacksmith class.
     *
     * @param name        the name of the Blacksmith
     * @param displayChar the character that will represent the Blacksmith in the display
     * @param hitPoints   the Blacksmith's starting hit points
     */
    public Blacksmith(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.UPGRADES);
    }

    @Override
    public List<String> getMonologueList(Actor actor){
        List<String> monologueList = new ArrayList<>();

        monologueList.add("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.");
        monologueList.add("It’s dangerous to go alone. Take my creation with you on your adventure!");
        monologueList.add("Ah, it’s you. Let’s get back to make your weapons stronger.");

        if (actor.hasCapability(Status.CARRIES_GREAT_KNIFE)){
            monologueList.add("Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.");
        }

        if (actor.hasCapability(Status.FELLED_ABXERVYER)){
            monologueList.add("Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.");
        } else {
            monologueList.add("Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!");
        }

        return monologueList;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        actions.add(new ListenAction(this));

        return actions;
    }
}
