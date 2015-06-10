package Game;

import java.awt.Point;

/**
 * This is the Interface for the mapChange() event which lets the Player
 * switch to an other Map
 * @author Florian Harzrian Harz
 */
public interface MapChange {

    public void mapChange(Point destination, String newMap);
}
