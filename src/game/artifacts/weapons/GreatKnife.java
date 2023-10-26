package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actors.friendly.merchants.quirks.RobQuirk;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.artifacts.Upgradable;
import game.artifacts.weapons.skills.Skill;
import game.artifacts.weapons.skills.StabAndStepSkill;
import game.artifacts.weapons.skills.WeaponSkill;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Class representing a GreatKnife weapon.
 * This class extends the WeaponItem class and implements the WeaponSkill and Sellable interface.
 */
public class GreatKnife extends WeaponItem implements WeaponSkill, Sellable, Upgradable {
    // Default attributes for the Great Knife
    private static final String DEFAULT_NAME = "Great Knife";
    private static final char DEFAULT_DISPLAY_CHAR = '>';
    private static final int DEFAULT_DAMAGE = 75;
    private static final String DEFAULT_VERB = "slashes";
    private static final int DEFAULT_HITRATE = 70;
    private static final int DEFAULT_GREAT_KNIFE_PRICE = 175;
    private static final double UPGRADE_HIT_RATE_INCREASE = 0.01;
    private static final int DEFAULT_UPGRADE_LIMIT = Integer.MAX_VALUE; // Value that is practically too high to become 0
    private static final int DEFAULT_UPGRADE_PRICE = 2000;
    private static final int DEFAULT_UPGRADED_HIT_RATE_INCREASE = 1;

    private int upgradeCount = 0;

    /**
     * Default constructor for the Great Knife class.
     * Initializes the Great Knife with default attributes.
     */
    public GreatKnife() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_DAMAGE, DEFAULT_VERB, DEFAULT_HITRATE);
    }

    /**
     * Constructor for the Great Knife class with custom attributes.
     *
     * @param name        Name to call the Great Knife in the UI
     * @param displayChar Character to represent the Great Knife in the UI
     * @param damage      Broadsword's starting damage
     * @param verb        The verb that the Great Knife will use in the UI
     * @param hitRate     The chance the weapon has at hitting the target
     */
    public GreatKnife(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * @return The default price that the player sells this item for
     */
    @Override
    public int getSellingPrice() {
        return DEFAULT_GREAT_KNIFE_PRICE;
    }

    /**
     * Tick method when the Great Knife is carried.
     *
     * @param currentLocation Current location of the Great Knife
     * @param actor           Actor carrying the Great Knife
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.addCapability(Status.CARRIES_GREAT_KNIFE);
    }

    /**
     * Tick method when the Great Knife is on the ground.
     *
     * @param currentLocation Current location of the Great Knife
     */
    @Override
    public void tick(Location currentLocation) {
        this.resetWeapon();
    }

    /**
     * Returns the skill with the actor to apply the skill to
     *
     * @param otherActor The actor to apply the skill to
     * @return The Skill
     */
    @Override
    public Skill getSkill(Actor otherActor){
        return new StabAndStepSkill(this, otherActor);
    }

    /**
     * Reset the attributes of the weapon following the skill effects if needed
     */
    @Override
    public void resetWeapon() {
        this.removeCapability(Status.SKILL_ACTIVE);
    }


    /**
     * Get a list of allowable actions for the Great Knife when it's in a specific location.
     *
     * @param otherActor The actor interacting with the Broadsword
     * @param location   The specific location of the Broadsword
     * @return ActionList containing allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();

        // Attack enemies and allow activation of this weapon's skill (only usable when confronting enemy)
        if (otherActor.hasCapability(Status.HOSTILE)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
            actions.add(new ActivateSkillAction(this.getSkill(otherActor)));
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
                    new RobQuirk(10) // The trader takes the item and also robs Runes from the player
            ));
        }

        return actions;
    }

    @Override
    public void upgrade() {
        this.upgradeCount++;
        this.increaseHitRate(DEFAULT_UPGRADED_HIT_RATE_INCREASE * this.upgradeCount);
    }

    @Override
    public boolean isUpgradable() {
        return this.upgradeCount < DEFAULT_UPGRADE_LIMIT;
    }

    @Override
    public int getUpgradePrice() {
        return DEFAULT_UPGRADE_PRICE;
    }

}
