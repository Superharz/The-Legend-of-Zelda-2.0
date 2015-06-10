package Moveable.Player;

import Inventory.Inventory;
import Moveable.Events;
import Game.Spot;
import Inventory.Items;
import Moveable.Mover;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * This is the main player class
 * It also takes care of his movement
 * @author Florian Harz
 */
public class Player extends Mover {

    ImageIcon[][] before = new ImageIcon[3][4];
    Inventory inventory;
    private boolean first = true;
    private String mapName;

    public Player() {
        try {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    before[i][j] = new ImageIcon(ImageIO.read(this.getClass().
                            getResource("/Pictures/player1" + j + "" + i + ".png")));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        inventory = new Inventory(1, 1);
    }

    @Override
    public void addListener(Events toAdd) {
        super.addListener(toAdd);
        inventory.addListener(toAdd);
    }

    @Override
    public void heal(int amount) {
        super.heal(amount);
        updateInvent();
    }

    public void use(Items item) {
        super.useItem(item);
        updateInvent();
    }

    public void setMapName(String name) {
        mapName = name;
    }

    public String getMapName() {
        return mapName;
    }

    public void updateInvent() {
        inventory.setStats(super.getLevel(), super.getHealth(), super.getDamage(), super.getArmor(), super.getSpeed());
    }

    @Override
    public int getWidth() {
        return this.before[0][0].getIconWidth();
    }

    @Override
    public void takeDamage(int damage) {
        if (!immortal) {
            this.live -= damage;
            if (this.live <= 0) {
                die();
            }
        }
        updateInvent();
    }

    public void setUP(Spot[][] spots) {
        if (first) {
            Rectangle r = new Rectangle(this.getX(), this.getY(), getWidth(), getWidth());
            this.setMover(10, 100000, 100, new Point(before[0][0].getIconWidth() / 2, before[0][0].getIconHeight() / 2), before[0], spots, r);
            super.setPlayer(1, 100);
            updateInvent();
            first = false;
        } else {
            setSpots(spots);
        }
    }

    @Override
    public void attack() {
        Rectangle r = null;
        if (lastDirection == 2) {
            r = new Rectangle(this.getX() + this.getWidth(), this.getY(), this.getWidth(), this.getWidth());
        }
        if (lastDirection == 3) {
            r = new Rectangle(this.getX() - this.getWidth(), this.getY(), this.getWidth(), this.getWidth());
        }
        if (lastDirection == 0) {
            r = new Rectangle(this.getX(), this.getY() + this.getWidth(), this.getWidth(), this.getWidth());
        }
        if (lastDirection == 1) {
            r = new Rectangle(this.getX(), this.getY() - this.getWidth(), this.getWidth(), this.getWidth());
        }
        for (Events hl : listeners) {
            hl.attacke(r);
        }
    }

    public void shoot() {
        super.shoot(true);
    }

    public boolean move(final int direction) {
        Thread t = new Thread() {
            public void run() {
                synchronized (this) {
                    move = true;
                    int j = 0, i = 0;
                    setIcon((before[1][direction]));
                    try {
                        while (move) {
                            switch (direction) {
                                case 3:
                                    moveLEFT();
                                    break;
                                case 2:
                                    moveRIGHT();
                                    break;
                                case 1:
                                    moveUP();
                                    break;
                                case 0:
                                    moveDOWN();
                                    break;
                            }
                            for (Events hl : listeners) {
                                hl.moved();
                                hl.playerMoved();
                            }
                            attack();
                            Thread.sleep(speed);
                            j++;
                            if (j == 24) {
                                j = 0;
                                i = (i + 1) % 2;
                                setIcon((before[i + 1][direction]));
                            }
                        }
                        setIcon((before[0][direction]));
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        t.start();
        return false;
    }

    public Point[] getLeft() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void die() {
        JOptionPane.showMessageDialog(null, "You died!");
        System.exit(00);
    }

    public void addItem(Items item) {
        inventory.addItem(item);
    }

    public Inventory getInventory() {
        return inventory;
    }
}
