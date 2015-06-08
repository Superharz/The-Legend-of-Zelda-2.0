package Moveable;

import Game.Image;
import Game.Spot;
import Inventory.Items;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;

/**
 *
 * @author Flo
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
    public final int DOWN = 0, UP = 1, RIGHT = 2, LEFT = 3;
    public boolean immortal = false;

    public Mover(int speed, int live, int damage, Point hotSpot, ImageIcon[] img, Rectangle hitBox) {
        super((img[0]));
        this.speed = speed;
        this.live = live;
        this.damage = damage;
        this.hotSpot = hotSpot;
        this.hitBox = hitBox;
        this.img = img;
        this.setVisible(false);
    }

    public Mover(int speed, int live, int damage, Point hotSpot, BufferedImage img, Rectangle hitBox, Spot[][] spots) {
        this(speed, live, damage, hotSpot, new ImageIcon(img), hitBox, spots, 0);
    }

    public Mover(int speed, int live, int damage, Point hotSpot, BufferedImage img, Rectangle hitBox, Spot[][] spots, int height) {
        this(speed, live, damage, hotSpot, new ImageIcon(img), hitBox, spots, height);
    }

    public int getLayer() {
        return height;
    }

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

    public Mover() {
        super();
    }

    @Override
    public int getWidth() {
        return this.img[DOWN].getIconWidth();
    }

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

    public void setSpots(Spot[][] spots) {
        this.spots = spots;
    }

    public void addListener(Events toAdd) {
        listeners.add(toAdd);
    }

    @Override
    public ImageIcon getImageIcon() {
        return img[0];
    }

    public void stopMoving() {
        move = false;
    }

    public void setChangeHeight(boolean canChangeHeight) {
        this.canChangeHeight = canChangeHeight;
    }

    public void setImmortal() {
        immortal = true;
    }

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

    public void updateHeight() {
        Point p = this.getHotSpot();
        height = spots[toSpots(p.y)][toSpots(p.x)].getHeight();
    }

    public void stop() {
        move = false;
    }

    public void attack() {
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public int getStrength() {
        return this.damage;
    }

    public void heal(int amount) {
        this.live += amount;
    }

    public void takeDamage(int damage) {
        if (!immortal) {
            this.live -= damage;
            if (this.live <= 0) {
                die();
            }
        }
    }

    public void spwan() {
        this.setVisible(true);
    }

    public int getHealth() {
        return live;
    }

    public void shoot(boolean friendly) {
        for (Events hl : listeners) {
            hl.spawnArrow(friendly, this.getX() + this.getWidth() / 2, this.getY() + this.getWidth() / 2, this.lastDirection, damage);
        }
    }

    public abstract void die();

    public Point getHotSpot() {
        return new Point(this.getLocation().x + hotSpot.x, this.getLocation().y + hotSpot.y);
    }

    public Point getPosition() {
        Point p = this.getHotSpot();
        Point spot = new Point(toSpots(p.x), toSpots(p.y));
        return spot;
    }

    public boolean isAlive() {
        return (live > 0);
    }

    private int toPixel(int spot) {
        return spot * spots[0][0].image().getWidth();
    }

    private int toSpots(int pixels) {
        return pixels / spots[0][0].image().getWidth();
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getArmor() {
        return armor;
    }

    public int getLevel() {
        return lvl;
    }

    public void setPlayer(int lvl, int armor) {
        this.lvl = lvl;
        this.armor = armor;
    }

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
