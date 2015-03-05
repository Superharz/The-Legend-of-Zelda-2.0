/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Moveable.Player.Player;
import Tools.ImagePanel;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author Flo
 */
public class Map extends ImagePanel implements Moveable.Player.PlayerEvent{
    Spot[][] spots;
    int width, height;
    BufferedImage img;
    Player player;
    
    
    public Map() {
        super();
        player = new Player();
        this.add(player);
        player.setVisible(true);
        player.addListener(this);
        javax.swing.JButton l = new javax.swing.JButton("TEST");
        this.add(l);
        l.setVisible(false);
        l.setVisible(true);
        
    }
    
    public void setUP(int width,int heights,int playerX, int playerY) {
        this.width = width;
        this.height = heights;
        
        //player.setText("Try");
        player.setLocation(100,100);
        spots = new Spot[heights][width];
    }
    
    public void addSpot(Spot spot,int x, int y) {
        spots[y][x] = spot;
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
       }
        setBackground(img);
        
    }
    
    @Override
    public void moveUP() {
        if (move(1))
        player.setLocation(player.getX(), player.getY()-1);
    }

    @Override
    public void moveDOWN() {
        if (move(0))
         player.setLocation(player.getX(), player.getY()+1);
    }

    @Override
    public void moveRIGHT() {
        if (move(2))
         player.setLocation(player.getX()+1, player.getY());
    }
    
    public boolean move(int direction) {
        int x = player.getWidth();
        int y = player.getHeight();
        int px = player.getLocation().x;
        int py = player.getLocation().y;
        if (direction == 3) {
            Point[] points  = {new Point(px-1,py),new Point(px-1,py + y/2),new Point(px-1,py + y)};
            for (Point point : points) {
                if (!spots[point.y / height][point.x / width].walk()) {
                    return false;
                }
            }
            
            
        }
        return true;
    }
    
    @Override
    public void moveLEFT() {
        
        if (move(3))
        //System.out.println("LEFT");
        player.setLocation(player.getX()-1, player.getY());
    }

    @Override
    public void attacke() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
