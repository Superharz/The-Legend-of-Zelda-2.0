/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable;

import Inventory.Items;
import Moveable.Enemies.Enemie;
import java.awt.Point;
import java.awt.Rectangle;

/**
 *
 * @author Flo
 */
public interface Events
{
    public void use(Items item);
    public void moved();
    public void playerMoved();
    public void attacke(Rectangle r);
    public void spawnArrow(boolean friendly,int x,int y,int direction, int damage);
    public void spawnEnemie(Point p, Enemie e);
    public void spawnItem(Point p, Items item);
    public void removeMover(Mover m);
    public void heal(int amount);
    public void teleport(Point destination);
    public void teleport(Point destination, String mapName);
    public void text(String text);
    
}
