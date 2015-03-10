/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable;

import Game.Spot;
import Moveable.Enemies.EnemieEvent;
import Moveable.Player.Player;
import Moveable.Player.PlayerEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Flo
 */
public abstract class Mover extends javax.swing.JLabel{
    public final List<PlayerEvent> listeners = new ArrayList<PlayerEvent>();
    public boolean move = false;
    public int speed;
    int live;
    int damage;
    Point hotSpot;
    Spot[][] spots;
    Rectangle hitBox;
    public BufferedImage[] img;
    public int lastDirection = 0;
    public final int DOWN = 0, UP = 1, RIGHT = 2, LEFT = 3;
    public boolean immortal = false;
    
    
    public Mover(int speed, int live, int damage, Point hotSpot, BufferedImage[] img, Rectangle hitBox) {
        super( new ImageIcon(img[0]));
        this.speed   = speed;
        this.live    = live;
        this.damage  = damage;
        this.hotSpot = hotSpot;
        this.hitBox  = hitBox;
        this.img     = img;
        this.setVisible(false);
        //this.setIcon((Icon) img);
    }
    
    public Mover(int speed, int live, int damage, Point hotSpot, BufferedImage img, Rectangle hitBox,Spot[][] spots) {
        super( new ImageIcon(img));
        this.speed   = speed;
        this.live    = live;
        this.damage  = damage;
        this.hotSpot = hotSpot;
        this.hitBox  = hitBox;
        this.img     = new BufferedImage[]{img,img,img,img};
        //this.setVisible(false);
        this.spots = spots;
    }
    public Mover() {
        super();
//        super((Icon) img[0]);
//        this.speed   = speed;
//        this.live    = live;
//        this.damage  = damage;
//        this.hotSpot = hotSpot;
//        this.hitBox  = hitBox;
//        this.img     = img;
        //this.setVisible(false);
        //this.setIcon((Icon) img);
    }
    @Override
    public int getWidth(){
        return this.img[DOWN].getWidth();
    }
    public void setMover(int speed, int live, int damage, Point hotSpot,  BufferedImage[] img, Spot[][] spots, Rectangle hitBox) {
        this.setIcon((new ImageIcon(img[DOWN])));
        this.speed   = speed;
        this.live    = live;
        this.damage  = damage;
        this.hotSpot = hotSpot;
        this.hitBox  = hitBox;
        this.img     = img;
        this.spots = spots;
    }
    
    public void addListener(PlayerEvent toAdd) {
        listeners.add(toAdd);
    }
    
    
    
    public void setImmortal() {
        immortal = true;
    }
    
    public boolean moveUP() {
        lastDirection = UP;
        if (collision(1)) {
            this.setLocation(this.getX(), this.getY()-1);
            hitBox.setLocation(this.getLocation());
            return true;
        }
        return false;
    }


    public boolean moveDOWN() {
        lastDirection = DOWN;
        if (collision(0)){
            this.setLocation(this.getX(), this.getY()+1);
            hitBox.setLocation(this.getLocation());
            return true;
        }
        return false;
    }


    public boolean moveRIGHT() {
        lastDirection = RIGHT;
        if (collision(2)){
            this.setLocation(this.getX()+1, this.getY());
            hitBox.setLocation(this.getLocation());
            return true;
        }
        return false;
        
        //System.out.println("MOVE");
        //this.update(this.getGraphics());
    }
    public boolean moveLEFT() {
        lastDirection = LEFT;
        //System.out.println(this.getLocation().x);
        if (collision(3)){
        //System.out.println("LEFT");
            setLocation(this.getX()-1, this.getY());
            hitBox.setLocation(this.getLocation());
            return true;
        }
        System.out.println("Collision");
        return false;
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
    
    public void takeDamage(int damage) {
        if (!immortal) {
            this.live -= damage;
            if (this.live <= 0)
               die();
        }
    }
    
    public void spwan() {
        this.setVisible(true);
    }
    
    public void shoot(boolean friendly) {
        for (PlayerEvent hl : listeners)
            hl.spawnArrow(friendly ,this.getX()+this.getWidth()/2,  this.getY()+this.getWidth()/2, this.lastDirection, damage);
    }
    
    public void die() {
        //this.setVisible(false);
    }
    
    public boolean isAlive() {
        return (live > 0);
    }
    
    public int getDamage() {
        return damage;
    }
    
    public boolean collision(int direction) {
        int x = this.getWidth();
        int y = this.getHeight();
        int px = this.getLocation().x;
        int py = this.getLocation().y;
        int w2 = spots[0][0].image().getWidth();
        int h2 = spots[0][0].image().getHeight();
        int width = spots[0].length;
        int height = spots.length;
//        if (px-1 <= 0 || py - 1<= 0 || px + x +1 >=spots[0][0].image().getWidth()*width || py + y + 1 >= spots[0][0].image().getHeight()*height)
//            return false;
        //Left
        if (direction == 3) {
            if (px-1 < 0)
                return false;
            Point[] points  = {new Point(px-1,py),new Point(px-1,py + y/2),new Point(px-1,py + y-1)};
            //System.out.println(px-1);
            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
            for (Point point : points) {
                
                if (!spots[point.y / h2][point.x / w2].walk()) {
                    return false;
                }
            }
            
            
        }
        //Right
        if (direction == 2) {
            if (px + x +1 >=w2*width)
                return false;
            Point[] points  = {new Point(px+1+x,py),new Point(px+1+x,py + y/2),new Point(px+1+x,py + y-1)};
            //System.out.println(px-1);
            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
            for (Point point : points) {
                
                if (!spots[point.y / h2][point.x / w2].walk()) {
                    return false;
                }
            }
            
            
        }
        //Up
        if (direction == 1) {
            if (py-1 < -1)
                return false;
            Point[] points  = {new Point(px+1,py-1),new Point(px+x/2,py-1),new Point(px + x-1,py-1)};
            //System.out.println(px-1);
            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
            for (Point point : points) {
                
                if (!spots[point.y / h2][point.x / w2].walk()) {
                    return false;
                }
            }
            
            
        }
        //Down
        if (direction == 0) {
            if (py + y + 1 < h2*height) {
               // return false;
            Point[] points  = {new Point(px+1,py + y+1),new Point(px+x/2,py + y+1),new Point(px + x-1,py + y+1)};
            //System.out.println(px-1);
            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
            for (Point point : points) {
                
                if (!spots[point.y / h2][point.x / w2].walk()) {
                    return false;
                }
            }
            }
            else if (py + y + 1 > h2*height)
                return false;
            
        }
        return true;
    }
    
//    public int collideWith() {
//        
//    }
    
    public boolean move(final int direction) {
        //Thread t = new Thread() {
            //public void run() {
                //move = true;
                //int j = 0, i = 0;
               boolean work = false;
                setIcon(new ImageIcon(img[direction]));
                //try {
                    //while(move) {
                        //for (EnemieEvent hl : listeners) {
                            switch (direction) {
                                case 3: work =  moveLEFT(); break;
                                case 2: work =  moveRIGHT();break;
                                case 1: work =  moveUP();   break;
                                case 0: work =  moveDOWN(); break;
                            }
                        //}
                                        
                        //Thread.sleep(speed);
//                        j++;
//                        if (j == 24) {
//                            j = 0;
//                            i = (i+1)%2;
//                            setIcon(new ImageIcon(before[i+1][direction]));
//                        }
                    //}
                    //setIcon(new ImageIcon(before[0][direction]));
                //} catch (InterruptedException ex) {
                //    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                //}
                
            //}
        //};
        //t.start();
        return work;
    }
    
}
