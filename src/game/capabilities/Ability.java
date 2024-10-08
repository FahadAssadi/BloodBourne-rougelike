package game.capabilities;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class
 */
public enum Ability {
    /**
     * This ability indicates that an actor has the capability to teleport.
     */
    TELEPORTS,
    /**
     * This ability suggests that an actor has the capability to walk on certain types of tiles or terrain
     * without encountering negative effects or hazards.
     */
    WALKS_SAFE_TILES,
    /**
     * This ability suggests that an actor has the capability to trade items.
     */
    TRADES,
    /**
     * This ability suggests that an actor has the capability to upgrade items.
     */
    UPGRADES,
    /**
     * This status indicates that an actor cannot die in the void.
     */
    VOID_PROOF,
}
