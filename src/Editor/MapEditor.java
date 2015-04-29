/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Editor;

import Events.Event;
import Game.Spot;
import Inventory.Items;
import Moveable.Enemies.Enemie;
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
        Point s = getPoint(evt);
        if (s == null) return;
        if (obj instanceof Spot) {
            System.out.println("Spots");
            Spot spot = (Spot)obj;
            this.addSpot(spot, s.x, s.y);
            this.build();
        }
        if (obj instanceof Items) {
            System.out.println("Item");
            Items item = (Items)obj;
            this.addItem( s.x, s.y, item);
            this.build();
        }
        if (obj instanceof Event) {
            System.out.println("Event");
            Event event = (Event)obj;
            this.addEvent( s.x, s.y, event);
            this.build();
        }
        if (obj instanceof Enemie) {
            System.out.println("Enemie");
            Enemie e = (Enemie)obj;
            this.addEnemy(e, s, false);
            this.build();
        }
    }
    private Point getPoint(MouseEvent evt) {
        Point p = evt.getPoint();
        int X = p.x, Y = p.y;

        Point s;
        s = new Point( this.toSpots(X), this.toSpots(Y));
        if (s.x < 0 || s.y <0 || s.x >= super.getSpots()[0].length || s.y >= super.getSpots().length) 
            return null;
        return s;
    }
    
}
