package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actors.merchants.quirks.NoQuirk;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.artifacts.weapons.skills.GreatSlamSkill;
import game.artifacts.weapons.skills.Skill;
import game.artifacts.weapons.skills.WeaponSkill;
import game.capabilities.Ability;
import game.capabilities.Status;

/**
 * Class representing a GiantHammer weapon.
 * This class extends the WeaponItem class and implements the WeaponSkill and Sellable interface.
 */
public class GiantHammer extends WeaponItem implements WeaponSkill, Sellable {
    private static final String DEFAULT_NAME = "Giant hammer";
    private static final char DEFAULT_DISPLAY_CHAR = 'P';
    private static final int DEFAULT_DAMAGE = 160;
    private static final String DEFAULT_VERB = "smashes";
    private static final int DEFAULT_HITRATE = 90;
    private static final int DEFAULT_GIANT_HAMMER_PRICE = 100;

    /**
     * Default constructor for the GiantHammer class.
     * Initializes the GiantHammer with default attributes.
     */
    public GiantHammer() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_DAMAGE, DEFAULT_VERB, DEFAULT_HITRATE);
    }

    /**
     * Constructor for the GiantHammer class with custom attributes.
     *
     * @param name        Name to call the GiantHammer in the UI
     * @param displayChar Character to represent the GiantHammer in the UI
     * @param damage      GiantHammer's starting damage
     * @param verb        The verb that the GiantHammer will use in the UI
     * @param hitRate     The chance the weapon has at hitting the target
     */
    public GiantHammer(String name, char displayChar, int damage, String verb, int hitRate) {
        super(name, displayChar, damage, verb, hitRate);
    }

    /**
     * Tick method when the Giant hammer is carried.
     *
     * @param currentLocation Current location of the Giant hammer
     * @param actor           Actor carrying the Giant hammer
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
    }

    /**
     * Tick method when the Giant hammer is on the ground.
     *
     * @param currentLocation Current location of the Giant hammer
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
    public Skill getSkill(Actor actor) {
        return new GreatSlamSkill(this, actor);
    }

    /**
     * Reset the attributes of the weapon following the skill effects if needed
     */
    @Override
    public void resetWeapon() {
        this.removeCapability(Status.SKILL_ACTIVE);
    }

    /**
     * @return The default price that the player sells this item for
     */
    @Override
    public int getSellingPrice() {
        return DEFAULT_GIANT_HAMMER_PRICE;
    }

    /**
     * Get a list of allowable actions for the Giant hammer when it's in a specific location.
     *
     * @param otherActor The actor interacting with the Giant hammer
     * @param location   The specific location of the Giant hammer
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
                    new NoQuirk() // No specific quirk for this transaction
            ));
        }

        return actions;
    }
}
