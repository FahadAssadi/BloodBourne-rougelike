package game.artifacts.consumables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;

public class Bloodberry extends Item implements Consumable{
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */

    private static final String DEFAULT_NAME = "Bloodberry";
    private static final char DEFAULT_DISPLAY_CHAR = '*';
    private static final boolean DEFAULT_PORTABILITY_STATUS = true;
    private static final int DEFAULT_MAXHEALTH_INCREASE = 5;

    public Bloodberry(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    @Override
    public void consume(Actor actor) {
        // Increase the actor's max health by the calculated amount
        actor.modifyAttributeMaximum(BaseActorAttributes.HEALTH,ActorAttributeOperations.INCREASE,DEFAULT_MAXHEALTH_INCREASE);
        // Remove the consumed Refreshing Vial from the actor's inventory
        actor.removeItemFromInventory(this);

    }
}
