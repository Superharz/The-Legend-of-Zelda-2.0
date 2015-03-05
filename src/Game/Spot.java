/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Events.Event;
import java.awt.image.BufferedImage;
    
/**
 *
 * @author Flo
 */
public class Spot{
    
    private BufferedImage img;
    private boolean walkable;
    
    public Spot(BufferedImage img, boolean walkable) {
      this.img = img;
      this.walkable = walkable;
    }
    
    public void addEvent(Event evt) {
        
    }
    public BufferedImage image() {
        return img;
    }
    
    public boolean walk() {
        return walkable;
    }
}
