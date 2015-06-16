package Moveable.Weapons;

import Game.Spot;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * This is an Arrow which can be shoot
 * @author Florian Harz
 */
public class Arrow extends Moveable.Mover {

    int direction;
    boolean friendly = true;
    /**
     * Creates a new Arrow
     * @param x The X-Coordinate of the Arrow
     * @param y The Y-Coordinate of the Arrow
     * @param direction The Direction of the Arrow
     * @param damage The Strength of the Arrow
     * @param arrow The imageIcon of the Arrow
     * @param spots The Spots of the Map the Arrow is in
     * @param height The actual Height of the Arrow
     */
    public Arrow(int x, int y, int direction, int damage, ImageIcon arrow, Spot[][] spots, int height) {
        super(100, 100, damage, new Point(arrow.getIconWidth() / 2, arrow.getIconHeight() / 2), arrow, new Rectangle(x, y, arrow.getIconWidth(), arrow.getIconHeight()), spots, height);
        this.setImmortal();
        this.direction = direction;
        this.setChangeHeight(false);
    }
    /**
     * Moves the Arrow into its Direction
     * @return True if the Arrow was able to Move
     */
    public boolean move() {
        return super.move(direction);
    }
    /**
     * Gets whether the Arrow is shot by the Player
     * @return True if the Arrow is shot by the Player
     */
    public boolean isFriendly() {
        return friendly;
    }
    /**
     * Toggles whether the Arrow is shot by the Player
     * @param friendly True if the Arrow is shot by the Player
     */
    public void setFriendly(boolean friendly) {
        this.friendly = friendly;
    }
    /**
     * Lets the Arrow Die
     * Don't ask me how but even Arrows can die...
     */
    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
