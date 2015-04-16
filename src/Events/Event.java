/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Inventory.Items;
import Moveable.Enemies.Enemie;
import Moveable.Events;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Flo
 */
public class Event {
    public final List<Events> listeners = new ArrayList<Events>();
    public static final int TELEPORT = 0, TEXT = 1, HEAL = 2, ITEM = 3, SPAWN = 4;
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
    
    public int getEventType() {
        return eventType;
    }
    
    public boolean callEvent() {
        switch (eventType) {
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
    
}
