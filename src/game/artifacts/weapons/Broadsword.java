package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.actions.SellAction;
import game.artifacts.Sellable;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Class representing a Broadsword weapon.
 * This class extends the WeaponItem class and implements the WeaponSkill interface.
 * Created By:
 * @author Fahad Assadi
 */
public class Broadsword extends WeaponItem implements WeaponSkill {
    // Default attributes for the Broadsword
    private static final String DEFAULT_NAME = "Broadsword";
    private static final char DEFAULT_DISPLAY_CHAR = '1';
    private static final int DEFAULT_DAMAGE = 110;
    private static final String DEFAULT_VERB = "slashes";
    private static final int DEFAULT_HITRATE = 80;
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int skillDuration;
    private int skillTimer;

    /**
     * Default constructor for the Broadsword class.
     * Initializes the Broadsword with default attributes.
     */
    public Broadsword() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_DAMAGE, DEFAULT_VERB, DEFAULT_HITRATE);
    }

    /**
     * Constructor for the BroadSword class with custom attributes.
     *
     * @param name        Name to call the Broadsword in the UI
     * @param displayChar Character to represent the Broadsword in the UI
     * @param damage      Broadsword's starting damage
     * @param verb        The verb that the Broadsword will use in the UI
     * @param hitRate     The chance the weapon has at hitting the target
     */
    public Broadsword(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }


    /**
     * Set the duration of the weapon skill.
     *
     * @param skillDuration Duration of the weapon skill
     */
    @Override
    public void setSkillDuration(int skillDuration) {
        this.skillDuration = skillDuration;
        this.skillTimer = 0;
    }

    /**
     * Tick method when the Broadsword is carried.
     *
     * @param currentLocation Current location of the Broadsword
     * @param actor           Actor carrying the Broadsword
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.processWeaponSkill(true);
    }

    /**
     * Tick method when the Broadsword is on the ground.
     *
     * @param currentLocation Current location of the Broadsword
     */
    @Override
    public void tick(Location currentLocation) {
        this.processWeaponSkill(false);
    }

    /**
     * Process the Broadsword's weapon skill.
     *
     * @param isHeld Indicates if the Broadsword is being held by an actor
     */
    @Override
    public void processWeaponSkill(boolean isHeld){
        if (this.hasCapability(Status.SKILL_ACTIVE)){
            if (!isHeld) {
                this.resetWeapon();

            } else {
                this.skillTimer++;

                if (this.skillTimer == this.skillDuration){
                    this.resetWeapon();
                }

            }
        }
    }

    /**
     * Reset the Broadsword's weapon skill.
     */
    @Override
    public void resetWeapon(){
        this.removeCapability(Status.SKILL_ACTIVE);
        this.skillTimer = 0;

        this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
        this.updateHitRate(DEFAULT_HITRATE);
    }

    /**
     * Get a list of allowable actions for the Broadsword when it's in an unspecified location.
     *
     * @param otherActor The actor interacting with the Broadsword
     * @return ActionList containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor){
        ActionList actions = new ActionList();

        actions.add(new FocusAction(this, this,0.1f,90, 20));

        return actions;
    }

    /**
     * Get a list of allowable actions for the Broadsword when it's in a specific location.
     *
     * @param otherActor The actor interacting with the Broadsword
     * @param location   The specific location of the Broadsword
     * @return ActionList containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();

        actions.add(new AttackAction(otherActor, location.toString(), this));

        return actions;
    }
}