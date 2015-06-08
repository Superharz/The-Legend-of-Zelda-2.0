package Moveable.Weapons;

import Game.Spot;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author f.harz
 */
public class Arrow extends Moveable.Mover {

    int direction;
    boolean friendly = true;

    public Arrow(int x, int y, int direction, int damage, BufferedImage arrow, Spot[][] spots) {
        this(x, y, direction, damage, arrow, spots, 0);
    }

    public Arrow(int x, int y, int direction, int damage, BufferedImage arrow, Spot[][] spots, int height) {
        super(100, 100, damage, new Point(arrow.getWidth() / 2, arrow.getHeight() / 2), arrow, new Rectangle(x, y, arrow.getWidth(), arrow.getHeight()), spots, height);
        this.setImmortal();
        this.direction = direction;
        this.setChangeHeight(false);
    }

    public boolean move() {
        return super.move(direction);
    }

    public boolean isFriendly() {
        return friendly;
    }

    public void setFrienfly(boolean friendly) {
        this.friendly = friendly;
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
