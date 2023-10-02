package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.artifacts.consumables.Runes;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.FollowBehaviour;
import game.actors.behaviours.WanderBehaviour;
import edu.monash.fit2099.engine.items.DropAction;
import game.artifacts.consumables.HealingVial;
import game.capabilities.Status;
import game.misc.displays.FancyMessage;
import game.positions.LockedGate;
import game.weather.Weather;
import game.weather.WeatherTypes;

/**
 * A class that represents a special type of enemy called "Forest Watcher"
 */
public class ForestWatcher extends Enemy {

    // Default attributes for the Forest Watcher
    private static final String DEFAULT_NAME = "Abxervyer, the Forest Watcher";
    private static final char DEFAULT_DISPLAY_CHAR = 'Y';
    private static final int DEFAULT_HITPOINTS = 2000;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 80;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 25;

    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "knocks";

    private static final int DEFAULT_RUNE_DROP_AMOUNT = 5000;

    private  int tickCounter = 0;

    // Custom attributes
    private Ground postDeathFormation;

    /** Default constructor for the Forest Watcher Class.
     *
     */
    public ForestWatcher() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Status.VOID_PROOF);
    }

    /**
     * Custom constructor for the Forest Watcher Class.
     *
     */
    public ForestWatcher(Ground postDeathFormation) {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.postDeathFormation = postDeathFormation;
        this.addCapability(Status.VOID_PROOF);
    }

    @Override
    protected void addBehaviours() {
        this.behaviours.put(1, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (tickCounter %3 == 0){
            Weather.setNextWeather();
        }
        tickCounter+=1;

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new Runes(DEFAULT_RUNE_DROP_AMOUNT)), DEFAULT_RUNES_DROP_RATE);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Once the boss is defeated, the location where the boss last stood turns into a Gate to the Ancient Wood
        map.locationOf(this).setGround(postDeathFormation);

        // Print message when the boss is defeated
        System.out.println(FancyMessage.BOSS_DIED);

        return super.unconscious(actor, map);
    }

    /**
     * Define allowable actions for the Forest Watcher based on the presence of hostile actors.
     *
     * @param otherActor The other actor (usually the player) to check for hostility.
     * @param direction  The direction in which the action is allowed.
     * @param map        The GameMap where the action occurs.
     * @return A list of allowable actions for the Hollow Solider.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            this.behaviours.put(2, new FollowBehaviour(otherActor));
        }
        return actions;
    }
}
