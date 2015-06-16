package Editor;

import java.awt.Point;

/**
 * This class is used by the Undo/Redo-Class It saves the Object that is
 * supposed to be overwritten with its location so that it can be restored
 * probably if needed
 *
 * @author Florian Harz
 */
public class ObjectHelp {

    public Point location;
    public int type;
    public Object obj;
    /**
     * Creates a Helper-Object with a Game-Object and it's Location on the Map
     * @param location The Point of the Game-Object
     * @param type The Type of the Game-Object
     * @param obj The Game-Object
     */
    public ObjectHelp(Point location, int type, Object obj) {
        this.location = location;
        this.type = type;
        this.obj = obj;
    }
}
