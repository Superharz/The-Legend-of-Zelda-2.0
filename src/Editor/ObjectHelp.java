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

    public ObjectHelp(Point location, int type, Object obj) {
        this.location = location;
        this.type = type;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return null;
    }

    public boolean equals(ObjectHelp object) {
        return false;
    }

    public void destroy() {
    }

    @Override
    public ObjectHelp clone() {
        return null;
    }
}
