package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actors.friendly.merchants.traders.quirks.ScamQuirk;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.artifacts.weapons.skills.GreatSlamSkill;
import game.artifacts.weapons.skills.Skill;
import game.artifacts.weapons.skills.WeaponSkill;
import game.capabilities.Ability;
import game.capabilities.Status;

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
        this.addCapability(Status.CARRIES_GIANT_HAMMER);
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

    @Override
    public Skill getSkill(Actor actor) {
        return new GreatSlamSkill(this, actor);
    }

    @Override
    public void resetWeapon() {
        this.removeCapability(Status.SKILL_ACTIVE);

    }

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

        // ENEMIES.
        if (otherActor.hasCapability(Status.HOSTILE)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
            actions.add(new ActivateSkillAction(this.getSkill(otherActor)));
        }

        if (otherActor.hasCapability(Ability.TRADES)) {
            actions.add(new SellAction(
                    new TransactionItem(this, this.getSellingPrice()),
                    new ScamQuirk(10)
            ));
        }

        return actions;
    }
}
