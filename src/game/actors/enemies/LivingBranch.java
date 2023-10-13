package game.actors.enemies;


import game.capabilities.Status;

/**
 * A specific enemy type representing a LivingBranch in the game.
 *
 * The LivingBranch class extends the Enemy class and represents a type of enemy in the game.
 * It defines its behaviors, droppable items, and default attributes.
 *
 * Created By: Ishan Ingolikar
 *
 */
public class LivingBranch extends Enemy{

    // Default attributes for the Red Wolf
    private static final String DEFAULT_NAME = "Living Branch";
    private static final char DEFAULT_DISPLAY_CHAR = '?';
    private static final int DEFAULT_HITPOINTS = 75;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 250;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 90;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "bites";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 10;
    private static final int DEFAULT_RUNE_DROP_AMOUNT = 25;
    private static final int DEFAULT_SUMMER_DAMAGE_MULTIPLIER = 3;
    private static final int DEFAULT_RAINY_DAMAGE_MULTIPLIER = 1;
    private static final int DEFAULT_ATTACK_BEHAVIOUR_PRIORITY = 1;
    private static final int DEFAULT_FOLLOW_BEHAVIOUR_PRIORITY = 2;
    private static final int DEFAULT_WANDER_BEHAVIOUR_PRIORITY = 999;

    /**
     * Default constructor for the LivingBranch Class.
     */
    public LivingBranch() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
        this.addCapability(Status.VOID_PROOF);
    }

    /**
     * Constructor for the Enemy class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public LivingBranch(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.VOID_PROOF);
    }

    @Override
    protected void addBehaviours() {

    }

    @Override
    protected void addDroppableItems() {

    }
}
