/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Events.Event;
import Inventory.Items;
import Moveable.Enemies.Enemie;
import Moveable.Mover;
import Moveable.Player.Player;
import Moveable.Weapons.Arrow;
import Tools.ImagePanel;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Flo
 */
public class Map extends ImagePanel implements Moveable.Events, java.io.Serializable{
    boolean pause = false, first = true;
    Spot[][] spots;
    Point playerPosition = new Point(-1, -1);
    int width, height;
    transient BufferedImage img;
    LinkedList<Enemie> enemies;
    LinkedList<Arrow> arrows;
    int spotWidth;
    transient Thread t;
    private int startX,startY;
    //private final Object obj = new Object();
    private boolean protection = false;
    /**
     * Creates new form Map
     */
    public Map() {
        //this(new Player());
        this.player = new Player();
        initComponents();
        player.addListener(this);
        
        enemies = new LinkedList();
        arrows = new LinkedList<Arrow>();
    }
    public Map(Player player) {
        this.player = player;
        
        initComponents();
        player.addListener(this);
        startX = toSpots(player.getLocation().x);
        startY = toSpots(player.getLocation().y);
        enemies = new LinkedList();
        arrows = new LinkedList<Arrow>();
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
        //player = new Moveable.Player.Player();
        this.add(player);
        //player.setBounds(100, 50, player.getWidth(), player.getHeight());
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
        if (playerX != -1 && playerY != -1) {
            startX = playerX;
            startY = playerY;
        }
        //System.out.println("Pixel: "+toPixel(5));
        player.setSize(player.getWidth(), player.getWidth());
        //player.setBounds(50, 100, player.getWidth(), player.getWidth());
        //player.setBounds(toPixel(playerX), toPixel(playerY), player.getWidth(), player.getWidth());
        //player.setLocation(toPixel(playerX), toPixel(playerY));
        //player.setText("Try");
        //player.setLocation(50,50);
        spots = new Spot[heights][width];
        //updatePlayerPosition();
    }
    public Point getPlayerPosition() {
        return playerPosition;
    }
    
    public void addSpot(Spot spot,int x, int y) {
        spots[y][x] = spot;
        if (first) {
            this.setSize(width * spots[y][x].image().getWidth(),  height * spots[y][x].image().getHeight());
            spotWidth = spot.image().getWidth();
            player.setBounds(toPixel(startX), toPixel(startY), player.getWidth(), player.getWidth());
            first = false;
        }
        player.setUP(spots);
        //System.out.println("Seted up");
        
        //enemie1.setUP(spots);
    }
    public void setAllSpots(Spot s) {
        for (int y = 0; y < spots.length; y++) {
            for (int x = 0; x < spots[0].length; x++) {
                addSpot(s.clone(), x, y);
            }
        }
    }
    private boolean updatePlayerPosition() {
        Point oldPosition = playerPosition;
        playerPosition = new Point(toSpots(player.getHotSpot().x),toSpots(player.getHotSpot().y));
        //System.out.println(playerPosition);
        return !oldPosition.equals(playerPosition);
    }
    public Spot getSpot(int x, int y) {
        return spots[y][x];
    }
    public LinkedList<Enemie> getEnemies() {
        return enemies;
    }
    public int getSpotWidth() {
        return spotWidth;
    }
   public void build() {
        int x = spots[0][0].image().getWidth();
        int y = spots[0][0].image().getHeight();
       img = new BufferedImage(x*width,y*height,BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        for (int i = 0; i < spots.length; i++) { //rows
            for (int j = 0; j < spots[i].length; j++) { //colums
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
    static {
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
    }
    public void moveLEFT() {
//        //System.out.println(player.getLocation().x);
//        if (move(3))
//        //System.out.println("LEFT");
//        player.setLocation(player.getX()-1, player.getY());
    }

    @Override
    public void attacke(Rectangle r) {
        playerAttack(r);
        //Graphics g = img.getGraphics();
        //g.drawRect(r.x, r.y, r.width, r.height);
        //this.setImage(img);
        
    }
    
    public Player getplayer() {
        return player;
    }
    /**
     * X and Y in Pixels
     * @param e
     * @param x
     * @param y 
     */
    public void addEnemy(Enemie e,int x, int y) {
        //this.addEnemy(e, new Point(x,y));
        enemies.add(e);
        this.add(e);
        e.addListener(this);
        e.setBounds(x, y, e.getWidth(), e.getWidth());
        //e.setLocation(x, y);
        
        e.setUP(spots);
        e.startMove();
        //System.out.println("Set up");
        //player.setLocation(50,50);
        //e.randomMove();
        
    }
    /**
     * Point in spots
     * @param e
     * @param p 
     */
    public void addEnemy(Enemie e,Point p) {
        e = e.clone();
        enemies.add(e);
        this.add(e);
        e.addListener(this);
        e.setBounds(toPixel(p.x), toPixel(p.y), e.getWidth(), e.getWidth());
        //e.setLocation(x, y);
        
        e.setUP(spots);
        e.startMove();
        //System.out.println("Set up");
        //player.setLocation(50,50);
        //e.randomMove();
        
    }
     /**
     * Point in spots
     * @param e
     * @param p 
     */
    public void addEnemy(Enemie e,Point p, boolean start) {
        e = e.clone();
        enemies.add(e);
        this.add(e);
        e.addListener(this);
        e.setBounds(toPixel(p.x), toPixel(p.y), e.getWidth(), e.getWidth());
        //e.setLocation(x, y);
        
        e.setUP(spots);
        if (start)
            e.startMove();
        //System.out.println("Set up");
        //player.setLocation(50,50);
        //e.randomMove();
        
    }
    
    public void addEvent(int x, int y, Event evt) {
        evt.addListener(this);
        spots[y][x].addEvent(evt);
    }
    public void addItem(int x, int y, Items item) {
        spots[y][x].additem(item);
        this.add(item);
        item.setBounds(toPixel(x), toPixel(y), item.getIcon().getIconWidth(), item.getIcon().getIconHeight());
        System.out.println("Item added!");
        
    }
    public void removeItem(int x, int y) {
        Items j;
        if (spots[y][x].hasItem()) {
        for (int i = 0; i < spots[y][x].itemLength(); i++) {
            j = spots[y][x].pickUp();
            this.remove(j);
            if (spots[y][x].hasItem()) break;
        }
        }
        //item.setBounds(toPixel(x), toPixel(y), item.getIcon().getIconWidth(), item.getIcon().getIconHeight());
        System.out.println("Item removed!");
        
    }
    public void removeEvent(int x, int y) {
        
            spots[y][x].removeEvents();
            System.gc();
        
        //item.setBounds(toPixel(x), toPixel(y), item.getIcon().getIconWidth(), item.getIcon().getIconHeight());
        System.out.println("Event removed!");
        
    }
    public void removeEnemie(int x, int y) {
            int X;
            int Y;
            Enemie e;
            for (int i = 0; i < enemies.size(); i++) {
                e = enemies.get(i);
                X = toSpots(e.getLocation().x);
                Y = toSpots(e.getLocation().y);
                if (X == x && Y == y) {
                    this.removeMover(e);
                }
            }
            System.gc();
        
        //item.setBounds(toPixel(x), toPixel(y), item.getIcon().getIconWidth(), item.getIcon().getIconHeight());
        System.out.println("Enemie removed!");
        
    }
    public Spot[][] getSpots() {
        return spots;
    }

    @Override
    public synchronized void moved() {
        if (!protection) {
            Rectangle enemieBox;
            Rectangle playerBox = player.getHitBox();
            for (Enemie enemie : enemies) {
                enemieBox = enemie.getHitBox();
                if (enemieBox == null) {
                    System.out.println("Null-enemie");
                         
                }
                else if (playerBox == null) {
                    System.out.println("Null-playerBox");
                         
                }
                
                else if (playerBox.intersects(enemieBox)) {

                        System.out.println("Got Damage, Live left: " + player.getHealth());
                        player.takeDamage(enemie.getStrength());
                        protection = true;
                        protect();
                        //enemies.get(i).takeDamage(enemies.get(i).getStrength());

                        return;

                }
            }
        }
    }
    
    private void protect() {
        Thread t = new Thread() {
            @Override
            public void run() {
                synchronized(this) {
                try {
                        Thread.sleep(5000);
                        protection = false;
                } catch (Exception ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                }   
            }
        };
        t.start();
    }
    
    public synchronized boolean playerAttack(Rectangle r) {
        Rectangle enemieBox;
        boolean killed = false;
        //Rectangle playerBox = player.getHitBox();
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getLayer() == player.getLayer()) {
                enemieBox = enemies.get(i).getHitBox();

                if (r.intersects(enemieBox)) {
                        killed = true;
                        enemies.get(i).takeDamage(player.getStrength());
                        //if (!enemies.get(i).isAlive()) {
                            //this.remove(enemies.get(i));
                            //enemies.get(i).stopMoving();
                            //enemies.remove(enemies.get(i));
                        //}


                }
            }
        }
        return killed;
    }
    
    public void move() {
        //lastDirection = direction;
            
        
         t = new Thread("Arrow") {
            @Override
            public void run() {
                //synchronized(this) {
                boolean move = true;
                
                
                try {
                    while(move) {
                        Thread.sleep(10);
                        for (int i = 0; i < arrows.size(); i++) {
                            
                            if (!arrows.get(i).move() || playerAttack(arrows.get(i).getHitBox())) {
                                removeMover(arrows.get(i));
//                                remove(arrows.get(i));
//                                arrows.remove(i);
                                
                                if (arrows.size() <= 0)
                                    move = false;
                                
                                
                            }
                            checkForPaused();
                                    
                        }
                    }

                } catch (Exception ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                }   
            //}
        };
        t.start();
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
    
    public synchronized void play(boolean play) {
        try {
        if (play) {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).resumeThread();
            }
            resumeThread();
        }
        else {
            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).pauseThread();
            }
            pauseThread();
        }
        } catch (Exception ex) {
            Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playerShoot() {
        spawnArrow(true, player.getX()+player.getWidth()/2,  player.getY()+player.getWidth()/2, player.lastDirection, player.getDamage());
    }
    
    @Override
    public void spawnArrow(boolean friendly,int x,int y,int direction, int damage) {
        try {
        BufferedImage arrow = null;
        
            
                arrow = ImageIO.read (this.getClass().
                            getResource("/Pictures/Arrow"+direction+".png"));
            
        
        Arrow a = new Arrow(x, y, direction, damage, arrow,spots, player.getLayer());
        arrows.add(a);
        this.add(a);
        //e.addListener(this);
        a.setBounds(x, y, arrow.getWidth(), arrow.getHeight());
        this.repaint();
        if (arrows.size() == 1)
            move();
        
        } catch (IOException ex) {
                Logger.getLogger(Map.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    @Override
    public void removeMover(Mover m) {
        if (m instanceof Enemie) {
            this.remove(m);
            enemies.remove(m);
        }
        if (m instanceof Arrow) {
            this.remove(m);
            arrows.remove(m);
        }
        repaint();
        System.gc();
    }

    @Override
    public void heal(int amount) {
        player.heal(amount);
        System.out.println("Got Healed, Live left: " + player.getHealth());
        
    }

    @Override
    public void teleport(Point destination) {
        player.setLocation(toPixel(destination.x), toPixel(destination.y) );
        player.updateHeight();
    }

    @Override
    public void teleport(Point destination, String mapName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void text(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int toPixel(int spot) {
        return spot* spotWidth;
    }
    public int toSpots(int pixels) {
        return pixels / spotWidth;
    }
    
    public Inventory getInventory(){
        return player.getInventory();
    }
    
    
    
    @Override
    public void playerMoved() {
        boolean moved = updatePlayerPosition();
        //System.out.println(moved);
        if (moved) {

                while(spots[playerPosition.y][playerPosition.x].hasItem()){
                
                    Items item = spots[playerPosition.y][playerPosition.x].pickUp();
                    player.addItem(item);
                    this.remove(item);
                    repaint();
                    System.gc();
                    
                    
                    
                    
                    

                System.out.println("Item picked UP!");
                }  
            //System.out.println("Moved");
            //System.out.println("Player:  " + playerPosition);
            //boolean evt = spots[playerPosition.x][playerPosition.y].hasEvent();
            //System.out.println("Event:  " + evt);
            spots[playerPosition.y][playerPosition.x].callEvents();
        
            
        }
    }

    @Override
    public void spawnEnemie(Point p, Enemie e) {
        addEnemy(e, p);
    }

    @Override
    public void spawnItem(Point p, Items item) {
        this.addItem(p.x, p.y, item);
        this.repaint();
    }

    @Override
    public void use(Items item) {
        player.use(item);
    }

    void reUpdate() {
         build();
        requestFocus();
        this.play(true);
        System.out.println(arrows);
//        for (int i = 0;arrows != null && i < arrows.size(); i++) {
//            removeMover(arrows.get(i));
//        }
        
        getplayer().getInventory().addEvent();
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).startMove();
        }
        move();
        
       
    }

    
    
}