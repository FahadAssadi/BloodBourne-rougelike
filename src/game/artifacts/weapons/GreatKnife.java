package game.artifacts.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.ActivateSkillAction;
import game.actions.AttackAction;
import game.actions.SellAction;
import game.actors.merchants.quirks.RobQuirk;
import game.artifacts.Sellable;
import game.artifacts.TransactionItem;
import game.artifacts.weapons.skills.Skill;
import game.artifacts.weapons.skills.StabAndStepSkill;
import game.artifacts.weapons.skills.WeaponSkill;
import game.capabilities.Ability;
import game.capabilities.Status;

public class GreatKnife extends WeaponItem implements WeaponSkill, Sellable {
    // Default attributes for the Great Knife
    private static final String DEFAULT_NAME = "Great Knife";
    private static final char DEFAULT_DISPLAY_CHAR = '>';
    private static final int DEFAULT_DAMAGE = 75;
    private static final String DEFAULT_VERB = "slashes";
    private static final int DEFAULT_HITRATE = 70;
    private static final int DEFAULT_GREAT_KNIFE_PRICE = 175;

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

    @Override
    public Skill getSkill(Actor otherActor){
        return new StabAndStepSkill(this, otherActor);
    }

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

        // ENEMIES.
        if (otherActor.hasCapability(Status.HOSTILE)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
            actions.add(new ActivateSkillAction(this.getSkill(otherActor)));
        }

        if (otherActor.hasCapability(Ability.TRADES)) {
            actions.add(new SellAction(
                    new TransactionItem(this, this.getSellingPrice()),
                    new RobQuirk(10)
            ));
        }

        return actions;
    }


}
