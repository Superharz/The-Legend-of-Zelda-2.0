/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Moveable.Enemies;

import Game.Spot;
import Moveable.Mover;
import Moveable.Player.Player;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Flo
 */
public  class Enemie extends Mover{
    
    public Enemie() {
        //super(speed, live, damage, hotSpot, img);
        try { 
             img = new BufferedImage[4];
        //for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                
            
            
                img[j] = ImageIO.read (this.getClass().
                        getResource("/Pictures/player1"+j+"0.png"));
            }
            
            //this.randomMove();
        }
        catch (IOException ex) {
                Logger.getLogger(Enemie.class.getName()).log(Level.SEVERE, null, ex);
            }
        //}
    }
    
    public void setUP(Spot[][] spots) {
        this.setMover(1000/24, 100, 100, new Point(0,0), img, spots);
        //randomMove();
    }
    
    public void randomMove() {
        //Thread t = new Thread() {
            //public void run() {
        try {
            move = true;
            boolean work = true;
            int d = getRandom();
                    while(move) {
                        //for (EnemieEvent hl : listeners) {
                            //work = move(0);
                        //}
                        setLocation(getX()-1, getY());
                        System.out.println(getX());
                        //this.wait(1000);
                        Thread.sleep(1000);
                        
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
            //}};
        //t.start();
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
    
    
    
    
}
