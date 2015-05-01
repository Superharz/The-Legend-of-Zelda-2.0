/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Events.Event;
import Inventory.Items;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.ImageIcon;
    
/**
 *
 * @author Flo
 */
public class Spot implements java.io.Serializable, Image{
    LinkedList<Event> events;
    LinkedList<Items> items;
    //private BufferedImage img;
    private ImageIcon img;
    private boolean walkable;
    private int height ;
    
    
    public Spot(BufferedImage img, boolean walkable) {
       this(new ImageIcon(img),walkable);
     
    }
    public Spot(BufferedImage img, int height) {
        this(new ImageIcon(img),height);
    }
    public Spot(ImageIcon img, boolean walkable) {
        this.img = img;
      //this.img = img;
      this.height = 0;
      this.walkable = walkable;
    }
    public Spot(ImageIcon img, int height) {
        this.img = img;
      //this.img = img;
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
    public LinkedList<Event> removeEvents() {
        if (events != null) {
            LinkedList<Event> evt = (LinkedList<Event>)events.clone();
            events = null;
            return evt;
        }
        return null;
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
        //JOptionPane.showMessageDialog(null, "HI", "Test", 1, img);
       
        return toBufferedImage(img);
    }
    private BufferedImage toBufferedImage(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
        icon.getIconWidth(),
        icon.getIconHeight(),
        BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0,0);
        g.dispose();
        return bi;
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
        if (!hasItem()) return 0;
        return items.size();
    }
    public boolean walk() {
        return walkable;
    }
    /**
     * 0 or bigger: Layer
     * -1 or smaller: Connection to one lower or one higher 
     * @return The Height of the Spot
     */
    public int getHeight() {
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
    @Override
    public Spot clone() {
        Spot s = new Spot(toBufferedImage(img), height);
        if (!walkable || height == 0) {
            s = new Spot(toBufferedImage(img), walkable);
        }
        if (hasEvent()) {
            for (int i = 0; i < events.size(); i++) {
                s.addEvent(events.get(i));
            }
        }
        if (hasItem()) {
            for (int i = 0; i < items.size(); i++) {
                s.additem(items.get(i));
            }
        }
        return s;
    }
    public ImageIcon getIcon() {
        return img;
    }
    public ImageIcon getImageIcon() {
        return img;
    }
}
