package Moveable.Weapons;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author f.harz
 */
public class Arrow extends Moveable.Mover{
    public Arrow(int x, int y, int direction,int damage, BufferedImage[] arrow) {
        super(100, -1, damage, null, arrow, new Rectangle(x, y, arrow[0].getWidth(), arrow[0].getHeight()));
    }
}
