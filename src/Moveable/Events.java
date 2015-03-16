/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable;

import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Flo
 */
public interface Events
{
    public void moved();
    public void attacke(Rectangle r);
    public void spawnArrow(boolean friendly,int x,int y,int direction, int damage);
    public void removeMover(Mover m);
    public void heal(int amount);
    public void teleport(Point destination);
    public void teleport(Point destination, String mapName);
    public void text(String text);
    
}
