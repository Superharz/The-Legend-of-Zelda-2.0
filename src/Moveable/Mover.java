package Moveable;

import Game.Image;
import Game.Spot;
import Inventory.Items;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 * This is the Mover-Class
 * Any Moving Object in the Game extends from this Class
 * To create a Mover you have to create a new Class that extends this Class
 * @author Florian Harz
 */
public abstract class Mover extends javax.swing.JLabel implements Image {

    public final List<Events> listeners = new ArrayList<Events>();
    public LinkedList<Items> stuff;
    public boolean move = false;
    public int speed;
    public int live;
    public int damage;
    public int armor;
    public int lvl;
    private int height = 0;
    private boolean canChangeHeight = true;
    Point hotSpot;
    Spot[][] spots;
    Rectangle hitBox;
    public ImageIcon[] img;
    public int lastDirection = 0;
    public static final int DOWN = 0, UP = 1, RIGHT = 2, LEFT = 3;
    public boolean immortal = false;
    /**
     * Creates a new Mover
     * @param speed The Speed of the Object
     * @param live The Health of the Object
     * @param damage The Strength of the Object
     * @param hotSpot The HotSpot (Origin) of the Object
     * @param img The ImageIcon of the Object
     * @param hitBox The HitBox (Rectangle) of the Object
     * @param spots The Spots from the Map for detection of Walls
     * @param height The current Height of the Object
     */
    public Mover(int speed, int live, int damage, Point hotSpot, ImageIcon img, Rectangle hitBox, Spot[][] spots, int height) {
        super((img));
        this.height = height;
        this.speed = speed;
        this.live = live;
        this.damage = damage;
        this.hotSpot = hotSpot;
        this.hitBox = hitBox;
        this.img = new ImageIcon[]{img, img, img, img};
        this.spots = spots;
    }
    /**
     * Creates a blank Mover 
     */
    public Mover() {
        super();
    }
    /**
     * Gets the current Height of the Object
     * @return The current Height of the Object
     */
    public int getLayer() {
        return height;
    }
    /**
     * Gets the Width of the Object
     * @return The Width of the Object
     */
    @Override
    public int getWidth() {
        return this.img[DOWN].getIconWidth();
    }
    /**
     * Sets the Information about this Object manually
     * @param speed The Speed of the Object
     * @param live The Health of the Object
     * @param damage The Strength of the Object
     * @param hotSpot The HotSpot (Origin) of the Object
     * @param img The ImageIcon of the Object
     * @param hitBox The HitBox (Rectangle) of the Object
     * @param spots The Spots from the Map for detection of Walls
     */
    public void setMover(int speed, int live, int damage, Point hotSpot, ImageIcon[] img, Spot[][] spots, Rectangle hitBox) {
        this.setIcon(((img[DOWN])));
        this.speed = speed;
        this.live = live;
        this.damage = damage;
        this.hotSpot = hotSpot;
        this.hitBox = hitBox;
        this.img = img;
        this.spots = spots;
    }
    /**
     * Sets the Spots of this Object
     * @param spots The Spots of the Map the Object is in
     */
    public void setSpots(Spot[][] spots) {
        this.spots = spots;
    }
    /**
     * Adds a Listener to the Object for further Event-Handel
     * @param toAdd The Listener to add to this Object
     */
    public void addListener(Events toAdd) {
        listeners.add(toAdd);
    }
    /**
     * Gets the ImageIcon of this Object
     * @return The ImageIcon of this Object
     */
    @Override
    public ImageIcon getImageIcon() {
        return img[0];
    }
    /**
     * Stops the Object from Moving
     */
    public void stopMoving() {
        move = false;
    }
    /**
     * Toggles whether the Object can move to a new Layer
     * @param canChangeHeight True if the Object can change its Height
     */
    public void setChangeHeight(boolean canChangeHeight) {
        this.canChangeHeight = canChangeHeight;
    }
    /**
     * Makes the Object IMMORTAL, That's a long time to live...
     */
    public void setImmortal() {
        immortal = true;
    }
    /**
     * Lets the Object move UP one Pixel if there's no Wall or other restrain
     * @return True if the Object was able to move UP
     */
    public boolean moveUP() {
        lastDirection = UP;
        if (collision(1)) {
            this.setLocation(this.getX(), this.getY() - 1);
            hitBox.setLocation(this.getLocation());
            updateHeight();
            return true;
        }
        return false;
    }
    /**
     * Lets the Object move DOWN one Pixel if there's no Wall or other restrain
     * @return True if the Object was able to move DOWN
     */
    public boolean moveDOWN() {
        lastDirection = DOWN;
        if (collision(0)) {
            this.setLocation(this.getX(), this.getY() + 1);
            hitBox.setLocation(this.getLocation());
            updateHeight();
            return true;
        }
        return false;
    }
    /**
     * Lets the Object move RIGHT one Pixel if there's no Wall or other restrain
     * @return True if the Object was able to move RIGHT
     */
    public boolean moveRIGHT() {
        lastDirection = RIGHT;
        if (collision(2)) {
            this.setLocation(this.getX() + 1, this.getY());
            hitBox.setLocation(this.getLocation());
            updateHeight();
            return true;
        }
        return false;
    }
    /**
     * Lets the Object move LEFT one Pixel if there's no Wall or other restrain
     * @return True if the Object was able to move LEFT
     */
    public boolean moveLEFT() {
        lastDirection = LEFT;
        if (collision(3)) {
            setLocation(this.getX() - 1, this.getY());
            hitBox.setLocation(this.getLocation());
            updateHeight();
            return true;
        }
        return false;
    }
    /**
     * Lets the Object use a given Item and adds the Stats of the Item to the
     * Objects own Stats
     * Removes the Item if it already has been used
     * @param item The Item to use
     */
    public void useItem(Items item) {
        if (stuff == null) {
            stuff = new LinkedList<Items>();
        }
        if (!stuff.contains(item)) {
            int[] stats = item.getBasicStats();
            stuff.add(item);
            damage += stats[Items.DAMAGE];
            live += stats[Items.LIVE];
            armor += stats[Items.ARMOR];
            speed += stats[Items.SPEED];
        } else {
            removeItem(item);
        }
    }
    /**
     * Lets the Object remove a given Item and removes the Stats of the Item 
     * from the Objects own Stats
     * @param item The Item to remove
     */
    public void removeItem(Items item) {
        if (stuff.contains(item)) {
            int[] stats = item.getBasicStats();
            stuff.remove(item);
            damage -= stats[Items.DAMAGE];
            live -= stats[Items.LIVE];
            armor -= stats[Items.ARMOR];
            speed -= stats[Items.SPEED];
        }
        if (stuff.isEmpty()) {
            stuff = null;
            System.gc();
        }
    }
    /**
     * Validates whether the Object could move to the given Position
     * @param x The Colum of the Destination Point
     * @param y The Row of the Destination Point
     * @return True if the Object can move there
     */
    public boolean movable(int x, int y) {
        if (x < spots[0].length && y < spots.length && x >= 0 && y >= 0) {
            if (!spots[y][x].walk()) {
                return false;
            }
            int layer = spots[y][x].getHeight();
            if (layer < 0) {
                if (height == -1 * layer || height == -1 * layer - 1) {
                    return true;
                }
            }
            if (height < 0) {
                if (layer == -1 * height || layer == -1 * height - 1) {
                    return true;
                }
            }
            if (layer != height) {
                return false;
            }
        }
        return true;
    }
    /**
     * Updates the Objects Height to the actual Height at its HotSpot
     */
    public void updateHeight() {
        Point p = this.getHotSpot();
        height = spots[toSpots(p.y)][toSpots(p.x)].getHeight();
    }
    @Deprecated
    public void attack() {
    }
    /**
     * Gets the HitBox of this Object
     * @return The HitBox (Rectangle) of this Object
     */
    public Rectangle getHitBox() {
        return hitBox;
    }
    /**
     * Gets the strength of this Object
     * @return The Strength of this Object
     */
    public int getStrength() {
        return this.damage;
    }
    /**
     * Heals the Object by a given Amount
     * @param amount The Amount to Heal the Object
     */
    public void heal(int amount) {
        this.live += amount;
    }
    /**
     * Lets the Object take a certain Amount of Damage
     * Kills the Object if Live <= 0
     * Does nothing if the Objject is IMMORTAL
     * @param damage The Amount of Damage for the Object to take
     */
    public void takeDamage(int damage) {
        if (!immortal) {
            this.live -= damage;
            if (this.live <= 0) {
                die();
            }
        }
    }
    /**
     * Spawns the Object on the Map
     * @deprecated 
     */
    public void spwan() {
        this.setVisible(true);
    }
    /**
     * Gets the Health of the Object
     * @return The Health of the Object
     */
    public int getHealth() {
        return live;
    }
    /**
     * Lets the Object shoot an Arrow
     * @param friendly True if the Object is the Player
     */
    public void shoot(boolean friendly) {
        for (Events hl : listeners) {
            hl.spawnArrow(friendly, this.getX() + this.getWidth() / 2, this.getY() + this.getWidth() / 2, this.lastDirection, damage);
        }
    }
    /**
     * Every Object has its own way to die, but I will live FOREVER
     */
    public abstract void die();
    /**
     * Gets the HotSpot of this Object in Point-Form (in Pixel)
     * @return The HotSpot of this Object as a Point (in Pixel)
     */
    public Point getHotSpot() {
        return new Point(this.getLocation().x + hotSpot.x, this.getLocation().y + hotSpot.y);
    }
    /**
     * Gets the Position of this Object (in Spots)
     * @return The Position of this Object (in Spots)
     */
    public Point getPosition() {
        Point p = this.getHotSpot();
        Point spot = new Point(toSpots(p.x), toSpots(p.y));
        return spot;
    }
    /**
     * Am I alive?
     * @return True if I still talk to you...
     */
    public boolean isAlive() {
        return (live > 0);
    }
    @Deprecated
    private int toPixel(int spot) {
        return spot * spots[0][0].image().getWidth();
    }
    /**
     * Converts the given Pixel-Value to Spot-Value
     * @param pixels The Pixel-Value to convert to Spot-Value
     * @return The Spot-Value of the Pixel-Value
     */
    private int toSpots(int pixels) {
        return pixels / spots[0][0].image().getWidth();
    }
    /**
     * Gets the Strength of the Object
     * @return The Strength of the Object
     */
    public int getDamage() {
        return damage;
    }
    /**
     * Gets the Speed of the Object
     * @return The Speed of the Object
     */
    public int getSpeed() {
        return speed;
    }
    /**
     * Gets the Armor of the Object
     * @return The Armor of the Object
     */
    public int getArmor() {
        return armor;
    }
    /**
     * Gets the Level of the Object
     * @return The Level of the Object
     */
    public int getLevel() {
        return lvl;
    }
    /**
     * Used to Set-Up the Player
     * @param lvl The Level of the Player
     * @param armor The Armor of the Player
     */
    public void setPlayer(int lvl, int armor) {
        this.lvl = lvl;
        this.armor = armor;
    }
    /**
     * Hight-Tech Collision-Detection 
     * @param direction The Direction this Object is moving
     * @return True if the Object Doesn't collide with anything
     */
    public boolean collision(int direction) {
        int x = this.getWidth();
        int y = this.getHeight();
        int px = this.getLocation().x;
        int py = this.getLocation().y;
        int w2 = spots[0][0].image().getWidth();
        int h2 = spots[0][0].image().getHeight();
        int width = spots[0].length;
        int verticalHeight = spots.length;
        //Left
        if (direction == LEFT) {
            if (px - 1 < 0) {
                return false;
            }
            Point[] points = {new Point(px - 1, py), new Point(px - 1, py + y / 2), new Point(px - 1, py + y - 1)};
            return checkPoints(points, w2, h2);
        }
        //Right
        if (direction == RIGHT) {
            if (px + x + 1 >= w2 * width) {
                return false;
            }
            Point[] points = {new Point(px + 1 + x, py), new Point(px + 1 + x, py + y / 2), new Point(px + 1 + x, py + y - 1)};
            return checkPoints(points, w2, h2);
        }
        //Up
        if (direction == UP) {
            if (py - 1 < -1) {
                return false;
            }
            Point[] points = {new Point(px + 1, py - 1), new Point(px + x / 2, py - 1), new Point(px + x - 1, py - 1)};
            return checkPoints(points, w2, h2);
        }
        //Down
        if (direction == DOWN) {
            if (py + y + 1 < h2 * verticalHeight) {
                Point[] points = {new Point(px + 1, py + y), new Point(px + x / 2, py + y), new Point(px + x - 1, py + y)};
                return checkPoints(points, w2, h2);
            } else if (py + y + 1 > h2 * verticalHeight) {
                return false;
            }
        }
        return true;
    }
    /**
     * Checks the given Points for if the object can move there
     * @param points An Array of the Points to check
     * @param w2 The Width of a Spot
     * @param h2 The HEight of a Spot
     * @return True if the Object can move to the given Point
     */
    private boolean checkPoints(Point[] points, int w2, int h2) {
        for (Point point : points) {
            Spot s = spots[point.y / h2][point.x / w2];
            if (!s.walk()) {
                return false;
            }
        }
        Spot s = spots[points[1].y / h2][points[1].x / w2];
        int h = s.getHeight();
        if (canChangeHeight) {
            if (s.getHeight() < 0) {
                if (height == -h || height == (-h - 1)) {
                    System.out.println("Special");
                    return true;
                }
            }
            if (height < 0) {
                if (h == -height || h == -height - 1) {
                    System.out.println("Player Special");
                    return true;
                }
            }
        }
        if (s.getHeight() != height) {
            return false;
        }
        return true;
    }
    /**
     * Lets the Object move into the given Direction
     * Updates the Object Icon
     * @param direction The Direction to Move
     * @return True if the Object was able to move into the given Direction
     */
    public boolean move(final int direction) {
        boolean work = false;
        setIcon((img[direction]));
        switch (direction) {
            case 3:
                work = moveLEFT();
                break;
            case 2:
                work = moveRIGHT();
                break;
            case 1:
                work = moveUP();
                break;
            case 0:
                work = moveDOWN();
                break;
        }
        return work;
    }
}
