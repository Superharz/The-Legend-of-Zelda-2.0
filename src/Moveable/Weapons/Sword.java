/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable.Weapons;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Flo
 */
public class Sword extends Moveable.Mover{
    
    public Sword(int x, int y, int direction,int damage, BufferedImage[] sword) {
        
        super(100, -1, damage, null, sword, new Rectangle(x, y, sword[0].getWidth(), sword[0].getHeight()));
    }
    
    
}
