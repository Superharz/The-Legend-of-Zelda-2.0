/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable.Player;

import Game.Inventory;
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
//import javax.swing.Timer;

/**
 *
 * @author Flo
 */
public class Player extends Mover{
    //Thread t;
    //private int lastDirection = 0;
    ImageIcon[][] before = new ImageIcon[3][4];
    transient Inventory inventory;
    //private final List<PlayerEvent> listeners = new ArrayList<PlayerEvent>();
    public Player() {
        //t = new Thread(this,"test");
        //super(speed, live, damage, hotSpot, hitBox, img);
        try {
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                
            
                before[i][j] = new ImageIcon(ImageIO.read (this.getClass(). 
                        getResource("/Pictures/player1"+j+""+i+".png")));
            }
        }
        
        } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        inventory = new Inventory(1,1);
    }
    
    @Override
    public void addListener(Events toAdd) {
        //listeners.add(toAdd);
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
    public void updateInvent() {
        inventory.setStats(super.getLevel(), super.getHealth(), super.getDamage(), super.getArmor(),super.getSpeed());
    }
    @Override
    public int getWidth(){
        return this.before[0][0].getIconWidth();
    }
    
    @Override
    public void takeDamage(int damage) {
        if (!immortal) {
            this.live -= damage;
            if (this.live <= 0)
               die();
        }
        updateInvent();
    }
    
    public void setUP(Spot[][] spots) {
        Rectangle r = new Rectangle(this.getX(), this.getY(),  getWidth(), getWidth());
        this.setMover(10, 100000, 100, new Point(before[0][0].getIconWidth()/2,before[0][0].getIconHeight()/2), before[0],spots,r);
        //inventory.setStats(1, 100000);
        super.setPlayer(1, 100);
        updateInvent();
        //this.setLocation(100,100);
    }
    
    @Override
    public void attack() {
        Rectangle r = null;
        if (lastDirection == 2) {
            r = new Rectangle(this.getX()+this.getWidth(), this.getY(), this.getWidth(),this.getWidth());
        
                    
        }
        if (lastDirection == 3) {
            r = new Rectangle(this.getX()-this.getWidth(), this.getY(), this.getWidth(),this.getWidth());
        
                    
        }
        if (lastDirection == 0) {
            r = new Rectangle(this.getX(), this.getY()+this.getWidth(), this.getWidth(),this.getWidth());
        
                    
        }if (lastDirection == 1) {
            r = new Rectangle(this.getX(), this.getY()-this.getWidth(), this.getWidth(),this.getWidth());
        
                    
        }
        for (Events hl : listeners)
            hl.attacke( r);
    }
    public void shoot() {
        super.shoot(true);
    }
    
    public void moveUp() {
//        //Timer t = new Timer(speed,Events)
//        move = true;
//        int j = 0, i = 0;
//        this.setIcon(new ImageIcon(img[UP]));
//        try {
//          while(move) {
//                for (Events hl : listeners)
//                   hl.moveUP();            
//                Thread.sleep(speed);
//                j++;
//                if (j == 24) {
//                    j = 0;
//                    i = (i+1)%2;
//                    this.setIcon(new ImageIcon(before[i+1][UP]));
//                }
//            }
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
//        }
    } 
    
    public void moveDown() {
//        move = true;
//        int j = 0, i = 0;
//        this.setIcon(new ImageIcon(img[DOWN]));
//        try {
//          while(move) {
//                for (Events hl : listeners)
//                   hl.moveDOWN();            
//                Thread.sleep(speed);
//            }
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void moveRight() {
//        move = true;
//        int j = 0, i = 0;
//        this.setIcon(new ImageIcon(img[RIGHT]));
//        try {
//          while(move) {
//                for (Events hl : listeners)
//                   hl.moveRIGHT();   
//                Thread.sleep(speed);
//            }
//        } catch (InterruptedException ex) {
//           Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
 
    
    public boolean move(final int direction) {
        //lastDirection = direction;
            
        
        Thread t = new Thread() {
            public void run() {
                synchronized(this) {
                move = true;
                int j = 0, i = 0;
               
                setIcon((before[1][direction]));
                try {
                    while(move) {
                        //for (Events hl : listeners) {
                            switch (direction) {
                                case 3: moveLEFT();break;//hl.moveLEFT(); break;
                                case 2: moveRIGHT();break;
                                case 1: moveUP();   break;
                                case 0: moveDOWN();  break;
                            }
                        for (Events hl : listeners) {
                            hl.moved();
                            hl.playerMoved();
                        }
                        attack();
                        //this.wait(speed);
                        //this.wait(0, speed);
                        Thread.sleep(speed);
                        j++;
                        //System.out.println(j);
                        if (j == 24) {
                            j = 0;
                            i = (i+1)%2;
                            setIcon((before[i+1][direction]));
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
    
    
    
    public void moveLeft() {
//        move = true;
//        int j = 0, i = 0;
//        //System.out.println("MOVE");
//        this.setIcon(new ImageIcon(img[LEFT]));
//        try {
//          while(move) {
//              //System.out.println("MOVE");
//                for (Events hl : listeners)
//                   hl.moveLEFT();            
//                Thread.sleep(speed);
//            }
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public Point[] getLeft() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void die() {
        JOptionPane.showMessageDialog(null, "You died!");
    }
    public void addItem(Items item) {
        inventory.addItem(item);
    }
    public Inventory getInventory() {
        return inventory;
    }
    
}