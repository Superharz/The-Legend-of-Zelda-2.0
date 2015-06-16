package Moveable.Enemies;

import Game.Image;
import Game.Spot;
import Moveable.Mover;
import Moveable.Player.Player;
import Moveable.Events;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This is the main Enemy Class
 * It supports two move methods:
 * Random-Move, Enemy moves randomly
 * Wall-Move, Enemy sticks to the walls
 * @author Florian Harz
 */
public class Enemie extends Mover implements Image {

    public static final int RANDOMMOVE = 0, WALLMOVE = 1;
    private int moveMethod;
    boolean pause = false;
    boolean defaultStats = true;

    @Deprecated
    public Enemie(int moveMethod) {
        try {
            img = new ImageIcon[4];
            for (int j = 0; j < 4; j++) {
                img[j] = new ImageIcon(ImageIO.read(this.getClass().
                        getResource("/Pictures/player1" + j + "0.png")));
            }
            this.moveMethod = moveMethod;
        } catch (IOException ex) {
            Logger.getLogger(Enemie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Creates a new Enemy with its own Icons and a given Move-Set
     * @param moveMethod The Move-Set of the Enemy
     * @param img An ImageIcon-Array with the Icons of the Enemy
     * I may only me a few Second old but I'm certainly faster in Math than you!
     */
    public Enemie(int moveMethod, ImageIcon[] img) {
        this.img = img;
        this.moveMethod = moveMethod;
    }
    /**
     * Sets the Stats of this Enemy
     * @param speed The Speed of the Object
     * @param live The Health of the Object
     * @param damage The Strength of the Object
     * @param armor The Armor of the Object
     * Level UP...
     */
    public void setStats(int live, int damage, int speed, int armor) {
        this.live = live;
        this.damage = damage;
        this.speed = speed;
        this.armor = armor;
        defaultStats = false;
        System.out.println("Stats Set");
        System.out.println("LIVE: " + live);
    }
    /**
     * Sets the Enemy up
     * @param spots The Spots of the Map the Enemy is on
     * I'm ready to roll...
     */
    public void setUP(Spot[][] spots) {
        Rectangle r = new Rectangle(this.getX(), this.getY(), getWidth(), getWidth());
        if (defaultStats) {
            this.setMover(1000 / 24, 100, 100, new Point(img[0].getIconWidth() / 2, img[0].getIconHeight() / 2), img, spots, r);
            System.out.println("Default");
        } else {
            this.setMover(speed, live, damage, new Point(img[0].getIconWidth() / 2, img[0].getIconHeight() / 2), img, spots, r);
        }
        System.out.println("LIVE: " + live);
    }
    /**
     * Gets the Width of this Enemy
     * @return The Width of this Enemy
     * Do I look fat in that dress Honey?...
     */
    @Override
    public int getWidth() {
        return this.img[0].getIconWidth();
    }
    /**
     * Lets the Mover die
     * You might have kill my Physically form but my Soul lives on FOREVER...
     */
    @Override
    public void die() {
        stopMoving();
        for (Events hl : listeners) {
            hl.removeMover(this);
        }
    }
    /**
     * Toggles the Enemy to Pause/Move
     * @param play True if the Enemy is supposed to Move
     * Move or don't Move, that's the Question...
     */
    public void play(boolean play) {
        pause = !play;
    }
    /**
     * Lets the Enemy start Moving
     * This is a small Step for an Enemy but an even smaller one for a JLabel...
     */
    public void startMove() {
        switch (moveMethod) {
            case RANDOMMOVE:
                randomMove();
                break;
            case WALLMOVE:
                wallMove();
                break;
        }
    }
    /**
     * Pauses the Enemy if he's supposed to to Pause
     * No one can Stop me...
     */
    private void checkForPaused() {
        synchronized (this) {
            while (pause) {
                try {
                    this.wait();
                } catch (Exception e) {
                }
            }
        }
    }

    public void pauseThread() throws InterruptedException {
        pause = true;
    }
    /**
     * Lets the Enemy resume moving
     * The show MUST go on...
     */
    public void resumeThread() {
        synchronized (this) {
            pause = false;
            this.notify();
        }
    }
    /**
     * Heavy Thread to let the Enemy move random through the Room
     * Was there Alcohol in that Drink?...
     */
    private void randomMove() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    move = true;
                    boolean work = true;
                    int direction = getRandom();
                    setIcon((img[direction]));
                    while (move) {
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
                        for (Events hl : listeners) {
                            hl.moved();
                        }
                        if (!work) {
                            direction = getRandom();
                            setIcon((img[direction]));
                            work = true;
                        }
                        Thread.sleep(10);
                        if (getRandom(100) == 50) {
                            direction = getRandom();
                            setIcon((img[direction]));
                            for (Events hl : listeners) {
                                hl.spawnArrow(false, getHotSpot().x, getHotSpot().y, direction, 100);
                            }
                        }
                        checkForPaused();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }
    /**
     * Heavy Thread to let the Enemy move among the Walls
     * May the Walls be with you...
     */
    private void wallMove() {
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    move = true;
                    boolean work = true;
                    int direction = getRandom();
                    setIcon((img[direction]));
                    while (move) {
                        switch (direction) {
                            case LEFT:
                                work = moveLEFT();
                                break;
                            case RIGHT:
                                work = moveRIGHT();
                                break;
                            case UP:
                                work = moveUP();
                                break;
                            case DOWN:
                                work = moveDOWN();
                                break;
                        }
                        for (Events hl : listeners) {
                            hl.moved();
                        }
                        if (!work) {
                            switch (direction) {
                                case LEFT:
                                    direction = DOWN;
                                    break;
                                case RIGHT:
                                    direction = UP;
                                    break;
                                case UP:
                                    direction = LEFT;
                                    break;
                                case DOWN:
                                    direction = RIGHT;
                                    break;
                            }
                            setIcon((img[direction]));
                            work = true;
                        } else {
                            if (direction == RIGHT) {
                                Point p = getPosition();
                                if (!movable(p.x - 1, p.y + 1) && collision(DOWN)) {
                                    direction = DOWN;
                                    setIcon((img[direction]));
                                }
                            } else if (direction == DOWN) {
                                Point p = getPosition();
                                if (!movable(p.x - 1, p.y - 1) && collision(LEFT)) {
                                    direction = LEFT;
                                    setIcon((img[direction]));
                                }
                            } else if (direction == LEFT) {
                                Point p = getPosition();
                                if (!movable(p.x + 1, p.y - 1) && collision(UP)) {
                                    direction = UP;
                                    setIcon((img[direction]));
                                }
                            } else if (direction == UP) {
                                Point p = getPosition();
                                if (!movable(p.x + 1, p.y + 1) && collision(RIGHT)) {
                                    direction = RIGHT;
                                    setIcon((img[direction]));
                                }
                            }
                        }
                        Thread.sleep(10);
                        checkForPaused();
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        t.start();
    }
    /**
     * Clones this Enemy
     * @return An exact Copy of this Enemy, you might not notice me but I'm 
     * different :=)
     */
    @Override
    public Enemie clone() {
        Enemie e = new Enemie(moveMethod, img);
        e.setStats(live, damage, speed, armor);
        return e;
    }
    /**
     * Gets a Random Integer within 0 and 3
     * @return A random chosen Integer within 0 and 3
     */
    private int getRandom() {
        return getRandom(3);
    }
    /**
     * Gets a Random Integer within 0 and the given Number
     * @param max The Highest Number possible to choose
     * @return A random chosen Integer within 0 and the given Number
     */
    private int getRandom(int max) {
        int min = 0;
        double seed = Math.random();
        double L = (double) min;
        double H = (double) max;
        double random = (H - L + 1) * seed + L;
        int answer = (int) random;
        return answer;
    }
}
