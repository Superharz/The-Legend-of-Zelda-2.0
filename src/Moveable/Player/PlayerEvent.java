/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable.Player;

import Moveable.Mover;
import java.awt.Rectangle;

/**
 *
 * @author Flo
 */
public interface PlayerEvent
{
    public void moved();
    public void attacke(Rectangle r);
    public void spawnArrow(boolean friendly,int x,int y,int direction, int damage);
    public void removeMover(Mover m);
    //public void ();
}
