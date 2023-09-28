package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.artifacts.quirks.NoQuirk;
import game.artifacts.weapons.skills.FocusSkill;
import game.artifacts.weapons.skills.Skill;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Class representing a Broadsword weapon.
 * This class extends the WeaponItem class and implements the WeaponSkill interface.
 * Created By:
 * @author Fahad Assadi
 */
public class Broadsword extends WeaponItem implements WeaponSkill, Sellable {
    // Default attributes for the Broadsword
    private static final String DEFAULT_NAME = "Broadsword";
    private static final char DEFAULT_DISPLAY_CHAR = '1';
    private static final int DEFAULT_DAMAGE = 110;
    private static final String DEFAULT_VERB = "slashes";
    private static final int DEFAULT_HITRATE = 80;
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private static final int DEFAULT_BROADSWORD_PRICE = 100;
    private Skill skill;
    private int skillTimer;

    /**
     * Default constructor for the Broadsword class.
     * Initializes the Broadsword with default attributes.
     */
    public Broadsword() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_DAMAGE, DEFAULT_VERB, DEFAULT_HITRATE);
        this.AddSkill();
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
        this.AddSkill();
    }

    /**
     * Tick method when the Broadsword is carried.
     *
     * @param currentLocation Current location of the Broadsword
     * @param actor           Actor carrying the Broadsword
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.SkillTimer();
    }

    /**
     * Tick method when the Broadsword is on the ground.
     *
     * @param currentLocation Current location of the Broadsword
     */
    @Override
    public void tick(Location currentLocation) {
        this.ResetWeapon();
    }

    @Override
    public void AddSkill(){
        this.skill = new FocusSkill(this, 0.1f, 90, 20);
    }

    @Override
    public void ResetWeapon() {
        this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
        this.updateHitRate(DEFAULT_HITRATE);

        this.skillTimer = 0;
        this.removeCapability(Status.SKILL_ACTIVE);
    }

    @Override
    public void SkillTimer() {
        if (!this.hasCapability(Status.SKILL_ACTIVE)){
            this.skillTimer++;

            if (this.skillTimer == 5){
                this.ResetWeapon();
            }
        }
    }

    /**
     * Get a list of allowable actions for the Broadsword when it's in an unspecified location.
     *
     * @param actor The actor interacting with the Broadsword
     * @return ActionList containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor){
        ActionList actions = new ActionList();

        actions.add(new ActivateSkillAction(this.skill));

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

        if (!otherActor.hasCapability(Status.FRIENDLY)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }


        if (otherActor.hasCapability(Ability.TRADES)) {
            actions.add(new SellAction(
                    new TransactionItem(this, DEFAULT_BROADSWORD_PRICE),
                    new NoQuirk()
            ));
        }

        return actions;
    }
}