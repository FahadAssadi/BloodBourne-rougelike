package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
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
import game.capabilities.Ability;
import game.capabilities.Status;
import game.misc.displays.FancyMessage;
import game.weather.Weather;
import game.weather.susceptibles.WeatherSusceptiblesManager;

/**
 * A class that represents a special type of enemy called "Forest Watcher."
 *
 * The ForestWatcher class extends the Enemy class and represents a unique boss enemy in the game.
 * It defines its behaviors, droppable items, and special characteristics related to weather and post-death effects.
 *
 * Created by:
 * @author Debashish Sahoo
 * Modified by:
 * @author Fahad Assadi
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
    private int tickCounter = 1;
    private static final int DEFAULT_ATTACK_BEHAVIOUR_PRIORITY = 1;
    private static final int DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY = 2;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 999;
    private static final int DEFAULT_TICKS_BEFORE_WEATHER_TRANSITION = 3;
    private static final int ZERO = 0;

    // Custom attributes
    private Ground postDeathFormation;

    /**
     * Default constructor for the Forest Watcher Class.
     */
    public ForestWatcher() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Ability.VOID_PROOF);
        this.addCapability(Status.BOSS);
    }

    /**
     * Custom constructor for the Forest Watcher Class.
     * @param postDeathFormation The gate that appears when the boss is killed and takes the player to the Ancient Woods
     */
    public ForestWatcher(Ground postDeathFormation) {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.postDeathFormation = postDeathFormation;
        this.addCapability(Ability.VOID_PROOF);
        this.addCapability(Status.BOSS);
    }

    /**
     * Adds the AttackBehaviour and WanderBehaviour to the behaviors map.
     */
    @Override
    protected void addBehaviours() {
        this.behaviours.put(DEFAULT_ATTACK_BEHAVIOUR_PRIORITY, new AttackBehaviour());
        this.behaviours.put(DEFAULT_WANDER_BEHAVIOUR_PRIORITY, new WanderBehaviour());
    }

    /**
     * Overrides the playTurn method to include weather effects and delegate to the parent class.
     *
     * @param actions    A collection of possible Actions for this Actor.
     * @param lastAction The Action this Actor took last turn. Can be used in conjunction with Action.getNextAction().
     * @param map        The map containing the Actor.
     * @param display    The I/O object to which messages may be written.
     * @return The action that the Forest Watcher should perform in that turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // we override playTurn here so we dont remove the boss when reset.
        String message = this.weatherEffects();
        if (message!=null) {
            display.println(message);
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Handles the Forest Watcher's control over the weather.
     * It processes weather state transitions based on the current weather state.
     *
     * @return A message describing any weather-related effects or null if no effect occurred.
     */
    public String weatherEffects(){
        // Getting the Weather Manager.
        WeatherSusceptiblesManager weatherSusceptiblesManager = WeatherSusceptiblesManager.getWeatherSusceptiblesManager();

        // Processing all the weather effects on all Weather Susceptible Objects.
        Weather.getWeather().getWeatherState().processWeatherState(weatherSusceptiblesManager); // Weather Stops changing after death.
        if (this.tickCounter++ % DEFAULT_TICKS_BEFORE_WEATHER_TRANSITION == ZERO){
             Weather.getWeather().weatherTransition();
        }
        return null;
    }

    /**
     * Adds droppable items (Runes) to the droppableItems map with their drop rates.
     */
    @Override
    protected void addDroppableItems() {
        this.droppableItems.put(new DropAction(new Runes(DEFAULT_RUNE_DROP_AMOUNT)), DEFAULT_RUNES_DROP_RATE);
    }

    /**
     * Returns the intrinsic weapon for the Forest Watcher.
     *
     * @return An IntrinsicWeapon representing the Forest Watcher's attack.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }

    /**
     * Overrides the unconscious method to handle post-death effects, including changing the ground at the boss's location.
     *
     * @param actor The actor that defeated this boss.
     * @param map   The game map in which the boss was defeated.
     * @return A message indicating the result of the unconscious action.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Once the boss is defeated, the location where the boss last stood turns into a Gate to the Ancient Wood
        map.locationOf(this).setGround(postDeathFormation);

        actor.addCapability(Status.DEFEATED_ABXERVYER);

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
            this.behaviours.put(DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY, new FollowBehaviour(otherActor));
        }
        return actions;
    }
}
