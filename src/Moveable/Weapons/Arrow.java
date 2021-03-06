package Moveable.Weapons;

import Game.Spot;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author f.harz
 */
public class Arrow extends Moveable.Mover{
    int direction;
    public Arrow(int x, int y, int direction,int damage, BufferedImage arrow,Spot[][] spots) {
        super(100, 100, damage, new Point(arrow.getWidth()/2, arrow.getHeight()/2), arrow, new Rectangle(x, y, arrow.getWidth(), arrow.getHeight()),spots);
        this.setImmortal();
        this.direction = direction;
    }
    
    public boolean move() {
        return super.move(direction);
    }
}
