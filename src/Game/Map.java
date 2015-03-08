/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Moveable.Enemies.Enemie;
import Moveable.Player.Player;
import Tools.ImagePanel;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
/**
 *
 * @author Flo
 */
public class Map extends ImagePanel implements Moveable.Player.PlayerEvent{
    Spot[][] spots;
    int width, height;
    BufferedImage img;
    LinkedList<Enemie> enemies;
    /**
     * Creates new form Map
     */
    public Map() {
        initComponents();
        player.addListener(this);
        
        enemies = new LinkedList();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        this.setLayout(null);
        player = new Moveable.Player.Player();
        this.add(player);
        player.setBounds(50, 50, player.getWidth(), player.getHeight());
        this.repaint();
       
        //enemie1 = new Moveable.Enemies.Enemie();

//        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
//        this.setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(111, 111, 111)
//                        .addComponent(player, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
//                    .addGroup(layout.createSequentialGroup()
//                        .addGap(143, 143, 143)
//                        //.addComponent(enemie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
//                        ))
//                .addContainerGap(227, Short.MAX_VALUE))
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(59, 59, 59)
//                .addComponent(player, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addGap(70, 70, 70)
//                //.addComponent(enemie1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap(139, Short.MAX_VALUE))
//        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    //private Moveable.Enemies.Enemie enemie1;
    private Moveable.Player.Player player;
    // End of variables declaration                   

public void setUP(int width,int heights,int playerX, int playerY) {
        this.width = width;
        this.height = heights;
        player.setSize(player.getWidth(), player.getWidth());
        //player.setText("Try");
        //player.setLocation(50,50);
        spots = new Spot[heights][width];
    }
    
    public void addSpot(Spot spot,int x, int y) {
        spots[y][x] = spot;
        this.setSize(width * spots[y][x].image().getWidth(),  height * spots[y][x].image().getHeight());
        player.setUP(spots);
        System.out.println("Seted up");
        //enemie1.setUP(spots);
    }
  
    
   public void build() {
        int x = spots[0][0].image().getWidth();
        int y = spots[0][0].image().getHeight();
       img = new BufferedImage(x*width,y*height,BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        for (int i = 0; i < spots.length; i++) {
            for (int j = 0; j < spots[i].length; j++) {
                if (spots[i][j] != null)
                    g.drawImage(spots[i][j].image(), j*x, i*y, this);
            }
            g.drawLine(0, i*y, x*width, i*y);
       }
        for (int i = 0; i < spots[0].length; i++) {
           g.drawLine(i*x, 0, i*x, y*height);
       }
        setBackground(img);
        
    }
    
    
    public void moveUP() {
//        if (move(1))
//        player.setLocation(player.getX(), player.getY()-1);
    }
//
    
    public void moveDOWN() {
//        if (move(0))
//         player.setLocation(player.getX(), player.getY()+1);
    }
//
   
    public void moveRIGHT() {
//        if (move(2))
//         player.setLocation(player.getX()+1, player.getY());
//        
//        //System.out.println("MOVE");
//        //this.update(this.getGraphics());
    }
//    
//    public boolean move(int direction) {
//        int x = player.getWidth();
//        int y = player.getHeight();
//        int px = player.getLocation().x;
//        int py = player.getLocation().y;
//        int w2 = spots[0][0].image().getWidth();
//        int h2 = spots[0][0].image().getHeight();
////        if (px-1 <= 0 || py - 1<= 0 || px + x +1 >=spots[0][0].image().getWidth()*width || py + y + 1 >= spots[0][0].image().getHeight()*height)
////            return false;
//        //Left
//        if (direction == 3) {
//            if (px-1 < 0)
//                return false;
//            Point[] points  = {new Point(px-1,py),new Point(px-1,py + y/2),new Point(px-1,py + y-1)};
//            //System.out.println(px-1);
//            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
//            for (Point point : points) {
//                
//                if (!spots[point.y / h2][point.x / w2].walk()) {
//                    return false;
//                }
//            }
//            
//            
//        }
//        //Right
//        if (direction == 2) {
//            if (px + x +1 >=w2*width)
//                return false;
//            Point[] points  = {new Point(px+1+x,py),new Point(px+1+x,py + y/2),new Point(px+1+x,py + y-1)};
//            //System.out.println(px-1);
//            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
//            for (Point point : points) {
//                
//                if (!spots[point.y / h2][point.x / w2].walk()) {
//                    return false;
//                }
//            }
//            
//            
//        }
//        //Up
//        if (direction == 1) {
//            if (py-1 < -1)
//                return false;
//            Point[] points  = {new Point(px+1,py-1),new Point(px+x/2,py-1),new Point(px + x-1,py-1)};
//            //System.out.println(px-1);
//            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
//            for (Point point : points) {
//                
//                if (!spots[point.y / h2][point.x / w2].walk()) {
//                    return false;
//                }
//            }
//            
//            
//        }
//        //Down
//        if (direction == 0) {
//            if (py + y + 1 < h2*height) {
//               // return false;
//            Point[] points  = {new Point(px+1,py + y+1),new Point(px+x/2,py + y+1),new Point(px + x-1,py + y+1)};
//            //System.out.println(px-1);
//            //System.out.println(points[1].x/width + "    |   " + points[0].y/height);
//            for (Point point : points) {
//                
//                if (!spots[point.y / h2][point.x / w2].walk()) {
//                    return false;
//                }
//            }
//            }
//            else if (py + y + 1 > h2*height)
//                return false;
//            
//        }
//        return true;
//    }
//    
    public void moveLEFT() {
//        //System.out.println(player.getLocation().x);
//        if (move(3))
//        //System.out.println("LEFT");
//        player.setLocation(player.getX()-1, player.getY());
    }

    @Override
    public void attacke(Rectangle r) {
        playerAttack(r);
        Graphics g = img.getGraphics();
        g.drawRect(r.x, r.y, r.width, r.height);
        this.setImage(img);
        
    }
    
    public Player getplayer() {
        return player;
    }
    
    public void addEnemy(Enemie e,int x, int y) {
        enemies.add(e);
        this.add(e);
        e.addListener(this);
        e.setBounds(x, y, e.getWidth(), e.getWidth());
        //e.setLocation(x, y);
        
        e.setUP(spots);
        e.randomMove();
        //System.out.println("Set up");
        //player.setLocation(50,50);
        //e.randomMove();
        
    }
    public Spot[][] getSpots() {
        return spots;
    }

    @Override
    public void moved() {
        Rectangle enemieBox;
        Rectangle playerBox = player.getHitBox();
        for (Enemie enemie : enemies) {
            enemieBox = enemie.getHitBox();
            if (playerBox.intersects(enemieBox)) {
                
                
                    //player.takeDamage(enemie.getStrength());
                    //enemies.get(i).takeDamage(enemies.get(i).getStrength());
                    System.out.println("Got Damage");
                    break;
                
            }
        }
    }
    public void playerAttack(Rectangle r) {
        Rectangle enemieBox;
        Rectangle playerBox = player.getHitBox();
        for (Enemie enemie : enemies) {
            enemieBox = enemie.getHitBox();
            if (r.intersects(enemieBox)) {
                
                    enemie.takeDamage(player.getStrength());
                
                
            }
        }
    }
    
}