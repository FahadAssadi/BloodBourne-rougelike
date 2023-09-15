package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * A class that represents a special type of enemy called "Forest Keeper"
 */
public class ForestKeeper extends Enemy{

    // Default attributes for the Forest Keeper
    private static final String DEFAULT_NAME = "Forest Keeper";
    private static final char DEFAULT_DISPLAY_CHAR = '8';
    private static final int DEFAULT_HITPOINTS = 125;
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 25;
    private static final int DEFAULT_INTRINSIC_WEAPON_HITRATE = 75;

    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "knocks";
    private static final int DEFAULT_HEAL_VIAL_DROP_RATE = 20;

    /** Default constructor for the Forest Keeper Class.
     *
     */
    public ForestKeeper() {
        super(DEFAULT_NAME, DEFAULT_DISPLAY_CHAR, DEFAULT_HITPOINTS);
    }

    /**
     * Custom constructor for the Forest Keeper Class.
     *
     * @param name        The name of the enemy.
     * @param displayChar The character representing the enemy in the display.
     * @param hitPoints   The enemy's starting hit points.
     */
    public ForestKeeper(String name, char displayChar, int hitPoints) {
        super(name,displayChar,hitPoints);
    }


    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(DEFAULT_INTRINSIC_WEAPON_DAMAGE, DEFAULT_INTRINSIC_WEAPON_VERB, DEFAULT_INTRINSIC_WEAPON_HITRATE);
    }
}
