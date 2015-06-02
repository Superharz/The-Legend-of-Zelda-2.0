/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Editor;

import static Editor.GUI.ENEMIES;
import static Editor.GUI.EVENTS;
import static Editor.GUI.ITEMS;
import static Editor.GUI.SPOTS;
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
    UnReDo unre;
    public MapEditor() {
        unre = new UnReDo();
    }




    @Override
    public String toString() {
        return null;
    }
    public boolean equals(MapEditor object) {
        return false;
    }

    @Override
    public MapEditor clone() {
        return null;
    }
    
    public void click (MouseEvent evt, Object obj, int type) {
        ObjectHelp help = click(evt, obj, type,null);
        unre.clearRedo();
        if (help != null)
            unre.addUndo(help); 
 
    }
    
    ObjectHelp click(MouseEvent evt, Object obj, int type, Point p) {
        Point s;
        if (p != null) {
           s = p;
        }
        else {
            s = getPoint(evt);
        }
        
        if (s == null) return null;
        ObjectHelp help = new ObjectHelp(s, type, null);
        //ObjectHelp help = new ObjectHelp(s, type, null);
        switch (type) {
            case SPOTS :       
                System.out.println("Spots");
                Spot spot = (Spot)obj;
                //object = this.getSpot(s.x, s.y);
                help  = new ObjectHelp(s, type, this.getSpot(s.x, s.y));
                this.addSpot(spot, s.x, s.y);
                this.build();
                ;break;
            case ITEMS:       
                System.out.println("Item");
                if (obj == null) {
                    
                    Items i  = this.removeItem(s.x, s.y);
                    return new ObjectHelp(s, type, i);
                }
                Items item = (Items)obj;
                this.addItem( s.x, s.y, item);
                this.build();
                ;break;
            case EVENTS  :       
                System.out.println("Event");
                if (obj == null) {
                    Event e = this.removeEvent(s.x, s.y);
                    //if (e!=null)
                        //this.remove(e.getJLabel());
                    return new ObjectHelp(s, type, e);
                }
                Event event = (Event)obj;
                this.addEvent( s.x, s.y, event);
//                ImageIcon i = event.getImageIcon();
                
                this.build();
//                BufferedImage img = this.getImage();
//                Graphics g = img.getGraphics();
//                g.drawImage(img, toPixel(s.x), toPixel(s.y), null);
//                //JLabel l = new JLabel(i);
//                //l.setBounds(toPixel(s.x), toPixel(s.y), i.getIconWidth(), i.getIconHeight());
//                //this.add(l);
//                //event.setJLabel(l);
//                this.validate();
//                this.repaint();
                ;break;
            case ENEMIES  :       
                System.out.println("Enemie");
                if (obj == null) {
                    Enemie e = this.removeEnemie(s.x, s.y);
                    return new ObjectHelp(s, type, e);
                }
                Enemie e = (Enemie)obj;
                this.addEnemy(e, s, false);
                this.build();
                ;break;
        }
        return help;
        //unre.addRedo(help);
        
    }
    public void undo() {
        ObjectHelp help = unre.undo();
        if (help == null) return;
        ObjectHelp redo = click(null, help.obj, help.type, help.location);
        if (redo != null)
            unre.addRedo(redo);
        this.build();
    }
    public void redo() {
        ObjectHelp help = unre.redo();
        if (help == null) return;
        ObjectHelp undo = click(null, help.obj, help.type, help.location);
        if (undo != null)
            unre.addUndo(undo);
        this.build();
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
