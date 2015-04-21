/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable.Enemies;

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
public  class Enemie extends Mover{
    public static final int RANDOMMOVE = 0, WALLMOVE = 1;
    private int moveMethod;
    boolean pause = false;
   
    //private final List<PlayerEvent> listeners = new ArrayList<PlayerEvent>();
    public Enemie(int moveMethod) {
        //super(speed, live, damage, hotSpot, img);
        try { 
             img = new ImageIcon[4];
        //for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                
            
            
                img[j] = new ImageIcon(ImageIO.read (this.getClass().
                        getResource("/Pictures/player1"+j+"0.png")));
            }
            this.moveMethod = moveMethod;
            //this.randomMove();
        }
        catch (IOException ex) {
                Logger.getLogger(Enemie.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
    }
    public Enemie(int moveMethod, ImageIcon[] img) {
        //super(speed, live, damage, hotSpot, img);
        
             this.img = img;
            this.moveMethod = moveMethod;
            //this.randomMove();
       
        //}
    }
    
    public void setUP(Spot[][] spots) {
        Rectangle r = new Rectangle(this.getX(), this.getY(),  getWidth(), getWidth());
        this.setMover(1000/24, 100, 100, new Point(img[0].getIconWidth()/2,img[0].getIconHeight()/2), img, spots,r);
        //Rectangle r = new Rectangle(10, 10, 10, 10);
        
        //this.setLocation(100,100);
        //randomMove();
    }
    
    @Override
    public int getWidth(){
        return this.img[0].getIconWidth();
    }
    
//    public void addListener(Events toAdd) {
//        listeners.add(toAdd);
//    }
    
    @Override
    public void  die() {
        stopMoving();
        for (Events hl : listeners)
            hl.removeMover(this);
        
    }
    public void play(boolean play) {
        pause = !play;
    }
    public void startMove() {
        switch (moveMethod) {
            case RANDOMMOVE: randomMove();break;
            case WALLMOVE  : wallMove();  break;
        }
    }
    
    private void checkForPaused() {
        synchronized (this) {
            while (pause) {
                try {
                    this.wait();
                } catch (Exception e) {}
            }
        }
    }

    public void pauseThread() throws InterruptedException {
        pause = true;
    }

    public void resumeThread() {
        synchronized(this) {
            pause = false;
            this.notify();
        }
    }
    
    private void randomMove() {
        Thread t = new Thread() {
            @Override
            public void run() {
                //synchronized(this) {
        try {
            
            move = true;
            boolean work = true;
            int direction = getRandom();
                setIcon((img[direction]));
                    while(move) {
                        //for (EnemieEvent hl : listeners) {
                            //work = move(0);
                        //}
                        //setLocation(getX()-1, getY());
                        //System.out.println(getX());
                        switch (direction) {
                                case 3: work = moveLEFT();break;//hl.moveLEFT(); break;
                                case 2: work = moveRIGHT();break;
                                case 1: work = moveUP();   break;
                                case 0: work = moveDOWN();  break;
                            }
                        for (Events hl : listeners)
                            hl.moved();
                        //this.wait(1000);
                        if (!work) {
                            direction = getRandom();
                            setIcon((img[direction]));
                            work = true;
                        }
                        Thread.sleep(10);
                        if (getRandom(100)==50) {
                            direction = getRandom();
                            setIcon((img[direction]));
                        }
                        checkForPaused();
//                        if (!work)
//                            move = false;
//                            d = getRandom();
//                        j++;
//                        if (j == 24) {
//                            j = 0;
//                            i = (i+1)%2;
//                            setIcon(new ImageIcon(before[i+1][direction]));
//                        }
                    }
                    //setIcon(new ImageIcon(before[0][direction]));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                //}
            }};
        t.start();
    }
    
    private void wallMove() {
        Thread t = new Thread() {
            @Override
            public void run() {
                //synchronized(this) {
        try {
            move = true;
            boolean work = true;
            int direction = getRandom();
                setIcon((img[direction]));
                    while(move) {
                        //for (EnemieEvent hl : listeners) {
                            //work = move(0);
                        //}
                        //setLocation(getX()-1, getY());
                        //System.out.println(getX());
                        
                        switch (direction) {
                                case LEFT : work = moveLEFT();break;//hl.moveLEFT(); break;
                                case RIGHT: work = moveRIGHT();break;
                                case UP   : work = moveUP();   break;
                                case DOWN : work = moveDOWN();  break;
                        }
                        for (Events hl : listeners)
                            hl.moved();
                        //this.wait(1000);
                        if (!work) {
                            switch (direction) {
                                case LEFT : direction = DOWN ;break;//hl.moveLEFT(); break;
                                case RIGHT: direction = UP   ;break;
                                case UP   : direction = LEFT ;   break;
                                case DOWN : direction = RIGHT;  break;
                        }
                            setIcon((img[direction]));
                            work = true;
                        }
                        else {
                        if (direction == RIGHT ) {
                            Point p = getPosition();
                            if (!movable(p.x-1, p.y+1) && collision(DOWN)  ) {
                            
                                direction = DOWN;
                                setIcon((img[direction]));
                            }
                        }
                        else if (direction == DOWN) {
                            Point p = getPosition();
                            if (!movable(p.x-1, p.y-1) && collision(LEFT)) {
                                direction = LEFT;
                                setIcon((img[direction]));
                            }
                        }
                        else if (direction == LEFT) {
                            Point p = getPosition();
                            if (!movable(p.x+1, p.y-1) && collision(UP)) {
                                //System.out.println(p);
                                direction = UP;
                                setIcon((img[direction]));
                            }
                        }
                        else if (direction == UP) {
                            Point p = getPosition();
                            if (!movable(p.x+1, p.y+1) && collision(RIGHT)) {
                                direction = RIGHT;
                                setIcon((img[direction]));
                            }
                        }
                        }
                        Thread.sleep(10);
                        checkForPaused();
//                        if (getRandom(100)==50) {
//                            direction = getRandom();
//                            setIcon(new ImageIcon(img[direction]));
//                        }
//                        if (!work)
//                            move = false;
//                            d = getRandom();
//                        j++;
//                        if (j == 24) {
//                            j = 0;
//                            i = (i+1)%2;
//                            setIcon(new ImageIcon(before[i+1][direction]));
//                        }
                    }
                    //setIcon(new ImageIcon(before[0][direction]));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                //}
            }};
        t.start();
    }
    
   
    
    @Override
    public Enemie clone() {
        Enemie e = new Enemie(moveMethod);
        return e;
        
    }
    
    private  int getRandom( ) { 
        int min = 0;
        int max = 3;
         double seed = Math.random();
         double L = (double)min; 
         double H = (double)max; 
         double random = (H - L + 1) * seed + L; 
         int answer = (int)random;      
         return answer;   
    }
    private  int getRandom(int max ) { 
        int min = 0;
        //int max = 3;
         double seed = Math.random();
         double L = (double)min; 
         double H = (double)max; 
         double random = (H - L + 1) * seed + L; 
         int answer = (int)random;      
         return answer;   
    }
    
    
    
}
