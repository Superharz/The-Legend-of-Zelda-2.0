package Game;

import java.awt.Point;

/**
 * This is the Interface for the mapChange() event which lets the Player
 * switch to an other Map
 * @author Florian Harzrian Harz
 */
public interface MapChange {
    /**
     * Event to change the Map
     * @param destination The Destination for the Player on the new Map
     * @param newMap The Map-Name of the new Map
     */
    public void mapChange(Point destination, String newMap);
}
