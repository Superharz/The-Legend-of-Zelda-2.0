/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Editor;

import Game.Spot;
import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *
 * @author f.harz
 */
public class MapEditor extends Game.Map{
    
    public MapEditor() {
        
    }




    @Override
    public String toString() {
        return null;
    }
    public boolean equals(MapEditor object) {
        return false;
    }
    public void destroy() {

    }
    @Override
    public MapEditor clone() {
        return null;
    }

    void click(MouseEvent evt, Object obj) {
        if (obj instanceof Spot) {
            Spot spot = (Spot)obj;
            Point p = evt.getPoint();
            int X = p.x, Y = p.y;
            Point s;
            s = new Point( this.toSpots(X), this.toSpots(Y));
            this.addSpot(spot, s.x, s.y);
        }
        
    }
}
