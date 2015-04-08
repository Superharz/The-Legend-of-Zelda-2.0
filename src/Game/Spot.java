/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Events.Event;
import Inventory.Items;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
    
/**
 *
 * @author Flo
 */
public class Spot{
    LinkedList<Event> events;
    LinkedList<Items> items;
    private BufferedImage img;
    private boolean walkable;
    private int height;
    public static final int CONNECTION = -1;
    
    
    public Spot(BufferedImage img, boolean walkable) {
      this.img = img;
      this.height = 0;
      this.walkable = walkable;
    }
    public Spot(BufferedImage img, int height) {
      this.img = img;
      this.walkable = true;
      this.height = height;
    }
    
    public void addEvent(Event evt) {
        if (events == null) {
            events = new LinkedList<Event>();
            events.add(evt);
        }
        else {
            events.add(evt);
        }
        System.out.println("Event added");
    }
    public void additem(Items item) {
        if (items == null) {
            items = new LinkedList<Items>();
            items.add(item);
        }
        else {
            items.add(item);
        }
        
        System.out.println("Item added");
    }
    public BufferedImage image() {
        return img;
    }
    
    public boolean hasEvent() {
        if (events == null) return false;
        return !events.isEmpty();
    }
    
    public boolean hasItem() {
        if (items == null) return false;
        return !items.isEmpty();
    }
    public int itemLength() {
        return items.size();
    }
    public boolean walk() {
        return walkable;
    }
    public int height() {
        return height;
    }
    public void callEvents() {
        if (events != null) {
            boolean alive;
        
            for (int i = 0; i < events.size(); i++) {
                alive = events.get(i).callEvent();
                if (!alive) {
                    events.remove(i);
                    System.out.println("Event removed!");
                }
            }
        
            if (events.isEmpty()) {
                events = null;
                System.gc();
            }
        }
    }
    public Items pickUp() {
        
        if (items.size() == 1) {
            Items item = items.remove();
            items = null;
            System.gc();
            return item;
        }
        else
            return items.remove();
    }
}
