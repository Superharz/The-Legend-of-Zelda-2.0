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
    /**
     * Creates a new Player with a Default Move-Animation
     */
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
    /**
     * Adds a Listener to the Player and its Inventory for further Event-Use
     * @param toAdd The Listener to Add
     */
    @Override
    public void addListener(Events toAdd) {
        super.addListener(toAdd);
        inventory.addListener(toAdd);
    }
    /**
     * Heals the Player by a given Amount
     * @param amount The Amount the Player to Heal
     */
    @Override
    public void heal(int amount) {
        super.heal(amount);
        updateInvent();
    }
    /**
     * Lets the Player use a given Item
     * @param item The Item to use
     */
    public void use(Items item) {
        super.useItem(item);
        updateInvent();
    }
    /**
     * Sets the Map-Name of the actual Map
     * @param name The Map-Name of the actual Map
     */
    public void setMapName(String name) {
        mapName = name;
    }
    /**
     * Gets the Name of the Map the Player is in right now
     * @return The Map-Name of the actual Map
     */
    public String getMapName() {
        return mapName;
    }
    /**
     * Updates the Players Stats
     */
    public void updateInvent() {
        inventory.setStats(super.getLevel(), super.getHealth(), super.getDamage(), super.getArmor(), super.getSpeed());
    }
    /**
     * Gets the Width of the Player
     * @return The Width of the Player
     */
    @Override
    public int getWidth() {
        return this.before[0][0].getIconWidth();
    }
    /**
     * Lets the Player take Damage and Kills him when his Health is <= 0
     * Updates the Players Stats
     * @param damage The Amount of Damage the Player took 
     */
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
    /**
     * Sets the Player up
     * @param spots The Spots of the Map the Player is in
     */
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
    /**
     * Lets the Player attack in the direction he looks rigth now
     */
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
    /**
     * Shoots an Arrow
     */
    public void shoot() {
        super.shoot(true);
    }
    /**
     * Heavy Thread to take care of the Players Movement
     * @param direction The Direction for the Player to Walk
     * @return False, just to override
     */
    @Override
    public boolean move(final int direction) {
        Thread t = new Thread() {
            @Override
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
    /**
     * No one can live FOREVER...
     * Shuts me Down :=(
     * For now...
     */
    @Override
    public void die() {
        JOptionPane.showMessageDialog(null, "You died!");
        System.exit(00);
    }
    /**
     * Adds an Item to the Players Inventory
     * @param item The Item to add to the Players Inventory
     */
    public void addItem(Items item) {
        inventory.addItem(item);
    }
    /**
     * Gets the Inventory of the Player
     * @return The Inventory of the Player
     */
    public Inventory getInventory() {
        return inventory;
    }
}
