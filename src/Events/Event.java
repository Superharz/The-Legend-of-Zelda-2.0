/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author Flo
 */
public class Event implements Serializable, Image{
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
    
    public Event(Point destiny) {
        this(destiny,"");
        
    }
    public Event(Point destiny, String mapName) {
        this.destiny = destiny;
        this.mapName = mapName;
        eventType = TELEPORT;
    }
    
    public Event(String text) {
        this.text = text;
        eventType = TEXT;
    }
    public Event(int healAmount) {
        this.healAmount = healAmount;
        eventType = HEAL;
    }
    
    public Event(Point destiny, Enemie e) {
        this.destiny = destiny;
        this.e = e;
        eventType = SPAWN;
    }
    public Event(Point destiny, Items item) {
        this.destiny = destiny;
        this.item = item;
        eventType = ITEM;
    }
    
    public void addEventCount(int eventCount) {
        this.eventCount = eventCount;
        count = 0;
    }
    
    public void addListener(Events toAdd) {
        listeners.add(toAdd);
    }
    public void resetListener() {
        listeners.clear();
    }
    public int getEventType() {
        return eventType;
    }
    public void setJLabel(JLabel label) {
        this.label = label;
    }
    public JLabel getJLabel() {
        return label;
    }
    public boolean callEvent() {
        switch (eventType) {
            case MAPTELEPORT: teleport(); break;
            case TELEPORT   : teleport(); break;
            case TEXT       : text(); break;
            case HEAL       : heal(); break;
            case SPAWN      : spawn(); break;
            case ITEM       : item(); break;
                
        }
        if (eventCount != NOCOUNT) {
            count++;
            if (count == eventCount)
                return false;
        }
        return true;
    }
    
    private void teleport() {
        if (EMPTY.equals(mapName)) {
            for (Events hl : listeners)
                hl.teleport(destiny);
        }
        else {
            for (Events hl : listeners)
                hl.teleport(destiny,mapName);
        }
    }
    private void text() {
        for (Events hl : listeners)
                hl.text(text);
    }
    
    private void heal() {
        for (Events hl : listeners)
                hl.heal(healAmount);
    }
 
    private void spawn() {
        for (Events hl : listeners)
                hl.spawnEnemie(destiny, e);
    }

    private void item() {
        for (Events hl : listeners)
                hl.spawnItem(destiny, item);
    }

    @Override
    public ImageIcon getImageIcon() {
        BufferedImage img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        int w = g.getFontMetrics().stringWidth("TELEPORT");
        img = new BufferedImage(w + 8, 32, BufferedImage.TYPE_INT_ARGB);
        g = img.getGraphics();
        g.setColor(Color.red);
        switch (eventType) {
            case MAPTELEPORT: g.drawString("TELEPORT", 4, 16);break;
            case TELEPORT: g.drawString("TELEPORT", 4, 16);break;
            case HEAL: g.drawString("HEAL", 4, 16);break;
            case SPAWN: g.drawString("SPAWN", 4, 16);break;
            case ITEM: g.drawString("ITEM", 4, 16);break;
            case TEXT: g.drawString("TEXT", 4, 16);break;
        }
        
        return new ImageIcon(img);
    }

    
}
