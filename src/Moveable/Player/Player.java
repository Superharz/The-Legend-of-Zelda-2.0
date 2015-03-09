/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable.Player;

import Game.Spot;
import Moveable.Mover;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
//import javax.swing.Timer;

/**
 *
 * @author Flo
 */
public class Player extends Mover{
    //Thread t;
    //private int lastDirection = 0;
    BufferedImage[][] before = new BufferedImage[3][4];
    //private final List<PlayerEvent> listeners = new ArrayList<PlayerEvent>();
    public Player() {
        //t = new Thread(this,"test");
        //super(speed, live, damage, hotSpot, hitBox, img);
        try {
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                
            
                before[i][j] = ImageIO.read (this.getClass(). 
                        getResource("/Pictures/player1"+j+""+i+".png"));
            }
        }
        
        } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    public void addListener(PlayerEvent toAdd) {
        //listeners.add(toAdd);
        super.addListener(toAdd);
    }
    
    @Override
    public int getWidth(){
        return this.before[0][0].getWidth();
    }
    
    public void setUP(Spot[][] spots) {
        Rectangle r = new Rectangle(this.getX(), this.getY(),  getWidth(), getWidth());
        this.setMover(10, 100, 100, new Point(0,0), before[0],spots,r);
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
        for (PlayerEvent hl : listeners)
            hl.attacke( r);
    }
    public void shoot() {
        super.shoot(true);
    }
    
    public void moveUp() {
//        //Timer t = new Timer(speed,PlayerEvent)
//        move = true;
//        int j = 0, i = 0;
//        this.setIcon(new ImageIcon(img[UP]));
//        try {
//          while(move) {
//                for (PlayerEvent hl : listeners)
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
//                for (PlayerEvent hl : listeners)
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
//                for (PlayerEvent hl : listeners)
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
               
                setIcon(new ImageIcon(before[1][direction]));
                try {
                    while(move) {
                        //for (PlayerEvent hl : listeners) {
                            switch (direction) {
                                case 3: moveLEFT();break;//hl.moveLEFT(); break;
                                case 2: moveRIGHT();break;
                                case 1: moveUP();   break;
                                case 0: moveDOWN();  break;
                            }
                        for (PlayerEvent hl : listeners)
                            hl.moved();
                        attack();
                        //this.wait(speed);
                        //this.wait(0, speed);
                        Thread.sleep(speed);
                        j++;
                        System.out.println(j);
                        if (j == 24) {
                            j = 0;
                            i = (i+1)%2;
                            setIcon(new ImageIcon(before[i+1][direction]));
                        }
                    }
                    setIcon(new ImageIcon(before[0][direction]));
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
//                for (PlayerEvent hl : listeners)
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
    
}