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
 *
 * @author Flo
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

    public Enemie(int moveMethod, ImageIcon[] img) {
        this.img = img;
        this.moveMethod = moveMethod;
    }

    public void setStats(int live, int damage, int speed, int armor) {
        this.live = live;
        this.damage = damage;
        this.speed = speed;
        this.armor = armor;
        defaultStats = false;
        System.out.println("Stats Set");
        System.out.println("LIVE: " + live);
    }

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

    @Override
    public int getWidth() {
        return this.img[0].getIconWidth();
    }

    @Override
    public void die() {
        stopMoving();
        for (Events hl : listeners) {
            hl.removeMover(this);
        }
    }

    public void play(boolean play) {
        pause = !play;
    }

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

    public void resumeThread() {
        synchronized (this) {
            pause = false;
            this.notify();
        }
    }

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

    @Override
    public Enemie clone() {
        Enemie e = new Enemie(moveMethod, img);
        e.setStats(live, damage, speed, armor);
        return e;
    }

    private int getRandom() {
        int min = 0;
        int max = 3;
        double seed = Math.random();
        double L = (double) min;
        double H = (double) max;
        double random = (H - L + 1) * seed + L;
        int answer = (int) random;
        return answer;
    }

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
