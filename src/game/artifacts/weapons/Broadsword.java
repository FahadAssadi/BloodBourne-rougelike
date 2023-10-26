package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.artifacts.*;
import game.actors.friendly.merchants.quirks.NoQuirk;
import game.artifacts.weapons.skills.FocusSkill;
import game.artifacts.weapons.skills.Skill;
import game.artifacts.weapons.skills.TimedWeaponSkill;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Class representing a Broadsword weapon.
 * This class extends the WeaponItem class and implements the TimedWeaponSkill and Sellable interface.
 * Created By:
 * @author Fahad Assadi
 */
public class Broadsword extends WeaponItem implements TimedWeaponSkill, Sellable, Upgradable {
    /*
     Keeps track of the number of turns the weapon's skill has been active for
     */
    private int skillTimer;

    // Default attributes for the Broadsword
    private static final String DEFAULT_NAME = "Broadsword";
    private static final char DEFAULT_DISPLAY_CHAR = '1';
    private static final int DEFAULT_DAMAGE = 110;
    private static final String DEFAULT_VERB = "slashes";
    private static final int DEFAULT_HITRATE = 80;
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private static final int DEFAULT_BROADSWORD_PRICE = 100;
    private static final int DEFAULT_SKILL_DURATION = 5;
    private static final float DEFAULT_DAMAGE_MULTIPLIER_INCREASE = 0.1f;
    private static final int DEFAULT_UPDATED_HITRATE = 90;
    private static final int DEFAULT_UPGRADE_LIMIT = 999999;
    private static final int DEFAULT_UPGRADE_PRICE = 1000;

    private int upgradeCount = 0;

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
     * @return The default price that the player sells this item for
     */
    @Override
    public int getSellingPrice() {
        return DEFAULT_BROADSWORD_PRICE;
    }

    /**
     * Tick method when the Broadsword is carried.
     *
     * @param currentLocation Current location of the Broadsword
     * @param actor           Actor carrying the Broadsword
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.processSkillTimer();
    }

    /**
     * Tick method when the Broadsword is on the ground.
     *
     * @param currentLocation Current location of the Broadsword
     */
    @Override
    public void tick(Location currentLocation) {
        this.resetWeapon();
    }

    /**
     * Returns the skill with the actor to apply the skill to
     *
     * @param actor The actor to apply the skill to
     * @return The Skill
     */
    @Override
    public Skill getSkill(Actor actor){
        return new FocusSkill(this, DEFAULT_DAMAGE_MULTIPLIER_INCREASE, DEFAULT_UPDATED_HITRATE);
    }

    /**
     * Reset the attributes of the weapon following the skill effects if needed
     */
    @Override
    public void resetWeapon() {
        this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
        this.updateHitRate(DEFAULT_HITRATE);

        this.skillTimer = 0;
        this.removeCapability(Status.SKILL_ACTIVE);
    }

    /**
     * Keeps track of the number of turns the weapon's skill can last for
     */
    @Override
    public void processSkillTimer() {
        if (this.hasCapability(Status.SKILL_ACTIVE)){
            this.skillTimer++;

            if (this.skillTimer == DEFAULT_SKILL_DURATION){
                this.resetWeapon();
            }
        }
    }

    @Override
    public void upgrade() {

    }

    @Override
    public boolean isUpgradable() {
        return true;
    }

    @Override
    public int getUpgradePrice() {
        return 0;
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

        actions.add(new ActivateSkillAction(this.getSkill(actor)));

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

        if (otherActor.hasCapability(Status.HOSTILE)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }

        /*
        Allow actor to Sell this item if they meet another actor who trades
         */
        if (otherActor.hasCapability(Ability.TRADES)) {
            /*
             Add a SellAction that takes in:
             - the Transaction Item, which takes in
                - this item
                - the Player's predefined selling price
             - a quirk (trick) that the trader may play on the player during the transaction
             */
            actions.add(new SellAction(
                    new TransactionItem(this, this.getSellingPrice()),
                    new NoQuirk() // No specific quirk for this transaction
            ));
        }

        return actions;
    }
}