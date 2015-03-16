/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

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
    public final int TELEPORT = 0, TEXT = 1, HEAL = 2, ITEM = 3, SPAWNE = 4;
    private final String EMPTY = "";
    private int eventType;
    Point destiny;
    String mapName;
    String text;
    int healAmount;
    
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
    
    public void addListener(Events toAdd) {
        listeners.add(toAdd);
    }
    
    public int getEventType() {
        return eventType;
    }
    
    public void callEvent() {
        switch (eventType) {
            case TELEPORT   : teleport(); break;
            case TEXT       : text(); break;
            case HEAL       : heal(); break;
                
        }
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
    
}
