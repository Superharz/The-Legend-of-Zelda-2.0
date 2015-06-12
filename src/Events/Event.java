package Events;

import Game.Image;
import Inventory.Items;
import Moveable.Enemies.Enemie;
import Moveable.Events;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This is the Event-Class An Event is something that will be activated when the
 * Player passes by it
 *
 * @author Florian Harz
 */
public class Event implements Serializable, Image {

    public final List<Events> listeners = new ArrayList<Events>();
    public static final int TELEPORT = 0, TEXT = 1, HEAL = 2, ITEM = 3, SPAWN = 4, MAPTELEPORT = 5;
    private final String EMPTY = "";
    private final int NOCOUNT = -1;
    private int eventType;
    private Enemie e;
    private Items item;
    Point destiny;
    String mapName;
    String text;
    int healAmount;
    private int eventCount = NOCOUNT;
    private int count;
    JLabel label;
    @Deprecated
    public Event(Point destiny) {
        this(destiny, "");
    }
    /**
     * Creates a new Teleport-Event which Teleports the User
     * @param destiny The Point of Destination
     * @param mapName The Map of Destination, null if it's the same Map
     */
    public Event(Point destiny, String mapName) {
        this.destiny = destiny;
        this.mapName = mapName;
        eventType = TELEPORT;
    }
    /**
     * Creates a new Text-Event which Displays a Text to the User
     * @param text The Text to display the User
     */
    public Event(String text) {
        this.text = text;
        eventType = TEXT;
    }
    /**
     * Creates a new Heal-Event which Heals th user
     * @param healAmount The Amount of Health-Points to heal the User
     */
    public Event(int healAmount) {
        this.healAmount = healAmount;
        eventType = HEAL;
    }
    /**
     * Creates a new Enemy-Event which Spawns an Enemy at a certain Point
     * @param destiny The Destination for the Enemy to spawn
     * @param e The Enemy to Spawn
     */
    public Event(Point destiny, Enemie e) {
        this.destiny = destiny;
        this.e = e;
        eventType = SPAWN;
    }
    /**
     * Creates a new Item-Event which Spawns an Item at a certain Point
     * @param destiny The Destination for the Item to spawn
     * @param item The Item to Spawn
     */
    public Event(Point destiny, Items item) {
        this.destiny = destiny;
        this.item = item;
        eventType = ITEM;
    }
    /**
     * Sets a Counter for this Event, how often it can be used
     * @param eventCount An Integer for the Amount this Event can be used
     */
    public void addEventCount(int eventCount) {
        this.eventCount = eventCount;
        count = 0;
    }
    /**
     * Adds a Listener to this Event which will activate it
     * @param toAdd The Listener to add to this Event
     */
    public void addListener(Events toAdd) {
        listeners.add(toAdd);
    }
    /**
     * Removes all the Listeners linked to this Event 
     */
    public void resetListener() {
        listeners.clear();
    }
    /**
     * Returns the Type of this Event
     * @return The Constant for this Events Type 
     */
    public int getEventType() {
        return eventType;
    }
    @Deprecated
    public void setJLabel(JLabel label) {
        this.label = label;
    }
    @Deprecated
    public JLabel getJLabel() {
        return label;
    }
    /**
     * Activates this Event and counts down the Event-Count
     * @return True if the Event can be still used one or more Times
     */
    public boolean callEvent() {
        switch (eventType) {
            case MAPTELEPORT:
                teleport();
                break;
            case TELEPORT:
                teleport();
                break;
            case TEXT:
                text();
                break;
            case HEAL:
                heal();
                break;
            case SPAWN:
                spawn();
                break;
            case ITEM:
                item();
                break;
        }
        if (eventCount != NOCOUNT) {
            count++;
            if (count >= eventCount) {
                return false;
            }
        }
        return true;
    }
    /**
     * Teleports the User by calling the same Method in the linked Map
     */
    private void teleport() {
        if (EMPTY.equals(mapName)) {
            for (Events hl : listeners) {
                hl.teleport(destiny);
            }
        } else {
            for (Events hl : listeners) {
                hl.teleport(destiny, mapName);
            }
        }
    }
    /**
     * Displays a Text to the User by calling the same Method in the linked Map
     */
    private void text() {
        for (Events hl : listeners) {
            hl.text(text);
        }
    }
    /**
     * Heals the User by calling the same Method in the linked Map
     */
    private void heal() {
        for (Events hl : listeners) {
            hl.heal(healAmount);
        }
    }
    /**
     * Spawns an Enemy by calling the same Method in the linked Map
     */
    private void spawn() {
        for (Events hl : listeners) {
            hl.spawnEnemie(destiny, e);
        }
    }
    /**
     * Spawns an Item by calling the same Method in the linked Map
     */
    private void item() {
        for (Events hl : listeners) {
            hl.spawnItem(destiny, item);
        }
    }
    /**
     * Creates an ImageIcon representing the Type of this Event
     * @return An ImageIcon with the Type of this Event
     */
    @Override
    public ImageIcon getImageIcon() {
        BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        int w = g.getFontMetrics().stringWidth("TELEPORT");
        img = new BufferedImage(w + 8, 32, BufferedImage.TYPE_INT_ARGB);
        g = img.getGraphics();
        g.setColor(Color.red);
        switch (eventType) {
            case MAPTELEPORT:
                g.drawString("TELEPORT", 4, 16);
                break;
            case TELEPORT:
                g.drawString("TELEPORT", 4, 16);
                break;
            case HEAL:
                g.drawString("HEAL", 4, 16);
                break;
            case SPAWN:
                g.drawString("SPAWN", 4, 16);
                break;
            case ITEM:
                g.drawString("ITEM", 4, 16);
                break;
            case TEXT:
                g.drawString("TEXT", 4, 16);
                break;
        }
        return new ImageIcon(img);
    }
}
