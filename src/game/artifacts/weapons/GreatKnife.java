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
import game.artifacts.quirks.ScamQuirk;
import game.artifacts.weapons.skills.FocusSkill;
import game.artifacts.weapons.skills.Skill;
import game.artifacts.weapons.skills.StabAndStepSkill;
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
    private Skill skill;
    private int skillTimer;

    /**
     * Default constructor for the Great Knife class.
     * Initializes the Great Knife with default attributes.
     */
    public GreatKnife() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_DAMAGE, DEFAULT_VERB, DEFAULT_HITRATE);
        this.AddSkill();
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
        this.AddSkill();
    }

    /**
     * Tick method when the Great Knife is carried.
     *
     * @param currentLocation Current location of the Great Knife
     * @param actor           Actor carrying the Great Knife
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.SkillTimer();
    }

    /**
     * Tick method when the Great Knife is on the ground.
     *
     * @param currentLocation Current location of the Great Knife
     */
    @Override
    public void tick(Location currentLocation) {
        this.ResetWeapon();
    }

    @Override
    public void AddSkill(){
    }

    @Override
    public void ResetWeapon() {
    }

    @Override
    public void SkillTimer() {
    }

    /**
     * Get a list of allowable actions for the Great Knife when it's in an unspecified location.
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
     * Get a list of allowable actions for the Great Knife when it's in a specific location.
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
                    new TransactionItem(this, DEFAULT_GREAT_KNIFE_PRICE),
                    new ScamQuirk(0.10)
            ));
        }

        return actions;
    }

}
