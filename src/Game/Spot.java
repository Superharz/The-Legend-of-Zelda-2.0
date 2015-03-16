/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Events.Event;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
    
/**
 *
 * @author Flo
 */
public class Spot{
    LinkedList<Event> events;
    private BufferedImage img;
    private boolean walkable;
    
    public Spot(BufferedImage img, boolean walkable) {
      this.img = img;
      this.walkable = walkable;
    }
    
    public void addEvent(Event evt) {
        if (events == null) {
            events = new LinkedList<Event>();
            events.add(evt);
        }
        else {
            events.add(evt);
        }
    }
    public BufferedImage image() {
        return img;
    }
    
    public boolean walk() {
        return walkable;
    }
    public void callEvents() {
        if (events != null) {
            for (int i = 0; i < events.size(); i++) {
                events.get(i).callEvent();
            }
        }
    }
}
