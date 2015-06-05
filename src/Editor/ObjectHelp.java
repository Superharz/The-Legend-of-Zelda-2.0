/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import java.awt.Point;

/**
 *
 * @author f.harz
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
