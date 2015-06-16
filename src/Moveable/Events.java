package Moveable;

import Inventory.Items;
import Moveable.Enemies.Enemie;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * This is an Interface to allow all kind of communication between the moving
 * Objects and the Game-Engine
 * @author Florian Harz
 */
public interface Events {
    /**
     * Lets the User use a given Item
     * @param item The Item to use
     */
    public void use(Items item);
    /**
     * Event that is called when something moved
     */
    public void moved();
    /**
     * Event that is called when the User moved
     */
    public void playerMoved();
    /**
     * Event that is called when the User attacks
     * @param r The Radius of the Attack
     */
    public void attacke(Rectangle r);
    /**
     * Event that is called when something spawns/shoots an Arrow
     * @param friendly True if the Arrow belongs to the Player
     * @param x The X-Coordinate of the Arrow
     * @param y The Y-Coordinate of the Arrow
     * @param direction The Direction of the Arrow as in Mover
     * @param damage The Strength of the Arrow
     */
    public void spawnArrow(boolean friendly, int x, int y, int direction, int damage);
    /**
     * Event that spawn an Enemy
     * @param e The Enemy to spawn
     * @param p The Point to Spawn the Enemy (in Spots) 
     */
    public void spawnEnemie(Point p, Enemie e);
    /**
     * Event that spawn an Item
     * @param item The Item to spawn
     * @param p The Point to Spawn the Item (in Spots) 
     */
    public void spawnItem(Point p, Items item);
    /**
     * Event to remove the given Mover from the Map
     * @param m The Mover to remove from the Map
     */
    public void removeMover(Mover m);
    /**
     * Event that Heals the Player
     * @param amount Amount of Live to restore at the Player
     */
    public void heal(int amount);
    /**
     * Event that Teleports the Player to a Point on the SAME Map
     * @param destination The Point of Destination for the Teleport (in Spots)
     */
    public void teleport(Point destination);    
    /**
     * Event that Teleports the Player to a Point on a DIFFERENT Map
     * @param destination The Point of Destination for the Teleport (in Spots)
     * @param mapName The name of the Map
     */
    public void teleport(Point destination, String mapName);
    /**
     * Event to display a given Text to the User
     * @param text The Text to display to the User
     */
    public void text(String text);
}
