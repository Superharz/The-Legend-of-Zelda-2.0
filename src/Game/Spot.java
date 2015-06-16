package Game;

import Events.Event;
import Inventory.Items;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import javax.swing.ImageIcon;

/**
 * This is the Spot-Class which is contained by a Map
 * It has it own Events and Items and can have different heights or can be a 
 * wall
 * @author Florian Harz
 */
public class Spot implements java.io.Serializable, Image {

    LinkedList<Event> events;
    LinkedList<Items> items;
    private ImageIcon img;
    private boolean walkable;
    private int height;
    @Deprecated
    public Spot(BufferedImage img, boolean walkable) {
        this(new ImageIcon(img), walkable);
    }
    @Deprecated
    public Spot(BufferedImage img, int height) {
        this(new ImageIcon(img), height);
    }
    /**
     * Creates a new Spot with Height == 0 or not walkable
     * @param img
     * @param walkable 
     */
    public Spot(ImageIcon img, boolean walkable) {
        this.img = img;
        this.height = 0;
        this.walkable = walkable;
    }
    /**
     * Creates a new Spot which has a given Height and is walkable
     * @param img The Image of the Spot
     * @param height The Height of the Spot
     */
    public Spot(ImageIcon img, int height) {
        this.img = img;
        this.walkable = true;
        this.height = height;
    }
    /**
     * Adds a given Event to the Spot
     * @param evt The Event to add to this Spot
     */
    public void addEvent(Event evt) {
        if (events == null) {
            events = new LinkedList<Event>();
            events.add(evt);
        } else {
            events.add(evt);
        }
        System.out.println("Event added");
    }
    /**
     * Removes all the Events from the Spot and returns them as a LinkesLIst
     * @return A LinkedList with all the Events from this Spot
     */
    public LinkedList<Event> removeEvents() {
        if (events != null) {
            LinkedList<Event> evt = (LinkedList<Event>) events.clone();
            events = null;
            return evt;
        }
        return null;
    }
    /**
     * Adds the given Item to the SPot
     * @param item The Item to add to the Spot
     */
    public void additem(Items item) {
        if (items == null) {
            items = new LinkedList<Items>();
            items.add(item);
        } else {
            items.add(item);
        }
        System.out.println("Item added");
    }
    /**
     * Returns the Image of the Spot as a BufferedImage
     * @return The BufferedImage of the Spot
     */
    public BufferedImage image() {
        return toBufferedImage(img);
    }
    /**
     * Converts the Image of the Spot to a BufferedImage
     * @param icon The ImageIcon to convert
     * @return A BufferedImage of the ImageIcon
     */
    private BufferedImage toBufferedImage(ImageIcon icon) {
        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bi;//Sometimes, bi is null, bug through Serilazation?
    }
    /**
     * Returns whether the Spot has any Events
     * @return True if the Spot has any Events
     */
    public boolean hasEvent() {
        if (events == null) {
            return false;
        }
        return !events.isEmpty();
    }
    /**
     * Returns whether the Spot has any Items
     * @return True if the Spot has any Items
     */
    public boolean hasItem() {
        if (items == null) {
            return false;
        }
        return !items.isEmpty();
    }
    /**
     * Gets the Amount of Items on the Spot
     * @return An Integer for the Amount of Items on the Spot
     */
    public int itemLength() {
        if (!hasItem()) {
            return 0;
        }
        return items.size();
    }
    /**
     * Gets whether you can walk on the Spot
     * @return True if you can walk on the Spot
     */
    public boolean walk() {
        return walkable;
    }

    /**
     * Gets the Height of this Spot
     * 0 or bigger: Layer -1 or smaller: Connection to one lower or one higher
     *
     * @return The Height of the Spot
     */
    public int getHeight() {
        return height;
    }
    /**
     * Calls all the Events of this Spot and removes them if wanted
     */
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
    /**
     * Gets one of the Items on this Spot and removes it from the Spot
     * @return An Item on this Spot
     */
    public Items pickUp() {
        if (items.size() == 1) {
            Items item = items.remove();
            items = null;
            System.gc();
            return item;
        } else {
            return items.remove();
        }
    }
    /**
     * Clones the Spot and returns the Copy
     * @return A Copy of this Spot
     * @deprecated 
     */
    @Override
    public Spot clone() {
        Spot s = new Spot(toBufferedImage(img), height);
        if (!walkable || height == 0) {
            s = new Spot(toBufferedImage(img), walkable);
        }
        if (hasEvent()) {
            for (Event event : events) {
                s.addEvent(event);
            }
        }
        if (hasItem()) {
            for (Items item : items) {
                s.additem(item);
            }
        }
        return s;
    }
    /**
     * Same as getimageIcon()
     */
    public ImageIcon getIcon() {
        return img;
    }
    /**
     * Gets the ImageIcon of the Spot
     * @return The ImageIcon of the Spot
     */
    @Override
    public ImageIcon getImageIcon() {
        return img;
    }
    /**
     * Destroys the Spot
     */
    void destroy() {
        if (events != null) {
            events.clear();
        }
        if (items != null) {
            items.clear();
        }
        img = null;
    }
}
