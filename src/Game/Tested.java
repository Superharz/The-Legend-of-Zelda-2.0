/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import IOUtil.Import;
import IOUtil.Serialize;
import Moveable.Player.Player;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.KeyStroke;
import javax.swing.JComponent;
/**
 *
 * @author Flo
 */
public class Tested extends javax.swing.JFrame {
    boolean move = false;
    Inventory i;
    boolean menu = false;
  
    /**
     * Creates new form Tested
     */
    public Tested() {
        this("C:\\Users\\f.harz\\Desktop\\player.sh");
//        Map temp = Serialize.xStreamIn(Map.class, "C:\\Users\\f.harz\\Desktop\\player.sh");
//        map1 = temp;
//        map1.reUpdate();
//        
//        Serialize.xStreamOut(map1.getSpot(1, 0), "C:\\Users\\f.harz\\Desktop\\spot1.she");
//        Serialize.xStreamOut(map1.getSpot(0, 0), "C:\\Users\\f.harz\\Desktop\\spot2.she");
        //map1.getplayer().getInventory().addEvent();
//        map1 = new Map();
//        //map1.setUP(10, 10, 5, 5);
//        map1.setUP(temp.width, temp.width, temp.getPlayerPosition().x, temp.getPlayerPosition().y);
//        //LinkedList<Enemie> e = temp.getEnemies();
//        for (int j = 0; j < temp.spots.length; j++) { //row
//            for (int k = 0; k < temp.spots[0].length; k++) {//colum
//                LinkedList<Event> evt = temp.getSpot(k, j).removeEvents();
//                map1.addSpot(temp.getSpot(k,j), k, j);
//                if (evt != null) {
//                    for (int l = 0; l < evt.size(); l++) {
//                        evt.get(l).resetListener();
//                        map1.addEvent(k, j, evt.get(l));
//                    }
//                }
//            }
//        }
//        for (int j = 0; j < e.size(); j++) {
//            map1.addEnemy(e.get(j), e.get(j).getLocation().x, e.get(j).getLocation().y);
//        }
//        Import m = new Import();
//        map1 = m.buildMap();
        //initComponents();
        //test();
        
//        try {
//            BufferedImage before = ImageIO.read (this.getClass().
//                    getResource("/Pictures/tile1.png"));
//            BufferedImage layer2 = ImageIO.read (this.getClass().
//                    getResource("/Pictures/tile3.png"));
//            BufferedImage plant = ImageIO.read (this.getClass().
//                    getResource("/Pictures/plant.png"));
//            BufferedImage before2 = ImageIO.read (this.getClass().
//                    getResource("/Pictures/tile2.png"));
//            BufferedImage sword = ImageIO.read (this.getClass().
//                    getResource("/Pictures/sword4.png"));
//            Spot s = new Spot(before, false);
//            Spot stair = new Spot(plant,-1);
//            Spot stair2 = new Spot(plant,-2);
//            Spot l1 = new Spot(layer2,1);
//            Spot l2 = new Spot(layer2,2);
//            map1.setUP(10, 10,5,5);
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    Spot a = new Spot(before2, true);
//                    map1.addSpot(a, i, j);
//                }
//            }
//            map1.addSpot(s, 0, 0);
//            map1.addSpot(s, 1, 1);
//            map1.addSpot(s, 8, 8);
//            map1.addSpot(s, 7, 8);
//            map1.addSpot(s, 8, 6);
//            map1.addSpot(s, 7, 6);
//            map1.addSpot(s, 9, 0);
//            map1.addSpot(l1, 8, 1);
//            map1.addSpot(l1, 7, 1);
//            map1.addSpot(l1, 6, 1);
//            map1.addSpot(l1, 5, 1);
//            map1.addSpot(stair, 4, 1);
//            map1.addSpot(stair2, 8, 2);
//            map1.addSpot(l2, 8, 3);
//            map1.addSpot(l2, 7, 3);
//            map1.addSpot(l2, 6, 3);
//            map1.addSpot(l2, 5, 3);
//            Event evt = new Event(new Point(5,1));
//            Enemie e2 = new Enemie(Enemie.WALLMOVE);
//            Event evt2 = new Event(new Point(2,5),e2);
//            Event evt3 = new Event(1000);
//            map1.addEvent(1, 0, evt2);
//            map1.addEvent(0, 1, evt);
//            map1.addEvent(9, 9, evt3);
//            ImageIcon icon = new ImageIcon(sword);
//            Items item = new Items("Wall","Wolf",icon,true);
//            item.addStats(new JLabel("Damage: 50"), Items.DAMAGE, 50);
//            Event evt4 = new Event(new Point(5,2),item);
//            evt4.addEventCount(1);
//            map1.addEvent(0, 9, evt4);
//            Enemie e;
//            
//            map1.build();
//            for (int i = 0; i < 5; i++) {
//                e = new Enemie(Enemie.RANDOMMOVE);
//                map1.addEnemy(e, 50+i*50, 50+i*50);
//            }
//            
//            //Enemie e = new Enemie(1000/240, 100, 100, new Point(0,0),map1.getSpots());
//            //map1.addEnemy(e, 100, 100);
////            Player p = new Player();
////            map1.add(p);
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Tested.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
       // map1.build();
        //map1.requestFocus();
//        this.validate();
//        this.repaint();
//        this.update(this.getGraphics());
//        this.setVisible(false);
//        this.setVisible(true);
    }
    public Tested (Map map1) {
        this.map1 = map1;
        initComponents();
        map1.build();
        map1.requestFocus();
        
        
    }
    public Tested (String file) {
        Map temp = Serialize.xStreamIn(Map.class, file);
        map1 = temp;
        map1.reUpdate();
        
        //Serialize.xStreamOut(map1.getSpot(1, 0), "C:\\Users\\f.harz\\Desktop\\spot1.she");
        //Serialize.xStreamOut(map1.getSpot(0, 0), "C:\\Users\\f.harz\\Desktop\\spot2.she");
        //map1.getplayer().getInventory().addEvent();
//        map1 = new Map();
//        //map1.setUP(10, 10, 5, 5);
//        map1.setUP(temp.width, temp.width, temp.getPlayerPosition().x, temp.getPlayerPosition().y);
//        //LinkedList<Enemie> e = temp.getEnemies();
//        for (int j = 0; j < temp.spots.length; j++) { //row
//            for (int k = 0; k < temp.spots[0].length; k++) {//colum
//                LinkedList<Event> evt = temp.getSpot(k, j).removeEvents();
//                map1.addSpot(temp.getSpot(k,j), k, j);
//                if (evt != null) {
//                    for (int l = 0; l < evt.size(); l++) {
//                        evt.get(l).resetListener();
//                        map1.addEvent(k, j, evt.get(l));
//                    }
//                }
//            }
//        }
//        for (int j = 0; j < e.size(); j++) {
//            map1.addEnemy(e.get(j), e.get(j).getLocation().x, e.get(j).getLocation().y);
//        }
//        Import m = new Import();
//        map1 = m.buildMap();
        initComponents();
        //test();
        
//        try {
//            BufferedImage before = ImageIO.read (this.getClass().
//                    getResource("/Pictures/tile1.png"));
//            BufferedImage layer2 = ImageIO.read (this.getClass().
//                    getResource("/Pictures/tile3.png"));
//            BufferedImage plant = ImageIO.read (this.getClass().
//                    getResource("/Pictures/plant.png"));
//            BufferedImage before2 = ImageIO.read (this.getClass().
//                    getResource("/Pictures/tile2.png"));
//            BufferedImage sword = ImageIO.read (this.getClass().
//                    getResource("/Pictures/sword4.png"));
//            Spot s = new Spot(before, false);
//            Spot stair = new Spot(plant,-1);
//            Spot stair2 = new Spot(plant,-2);
//            Spot l1 = new Spot(layer2,1);
//            Spot l2 = new Spot(layer2,2);
//            map1.setUP(10, 10,5,5);
//            for (int i = 0; i < 10; i++) {
//                for (int j = 0; j < 10; j++) {
//                    Spot a = new Spot(before2, true);
//                    map1.addSpot(a, i, j);
//                }
//            }
//            map1.addSpot(s, 0, 0);
//            map1.addSpot(s, 1, 1);
//            map1.addSpot(s, 8, 8);
//            map1.addSpot(s, 7, 8);
//            map1.addSpot(s, 8, 6);
//            map1.addSpot(s, 7, 6);
//            map1.addSpot(s, 9, 0);
//            map1.addSpot(l1, 8, 1);
//            map1.addSpot(l1, 7, 1);
//            map1.addSpot(l1, 6, 1);
//            map1.addSpot(l1, 5, 1);
//            map1.addSpot(stair, 4, 1);
//            map1.addSpot(stair2, 8, 2);
//            map1.addSpot(l2, 8, 3);
//            map1.addSpot(l2, 7, 3);
//            map1.addSpot(l2, 6, 3);
//            map1.addSpot(l2, 5, 3);
//            Event evt = new Event(new Point(5,1));
//            Enemie e2 = new Enemie(Enemie.WALLMOVE);
//            Event evt2 = new Event(new Point(2,5),e2);
//            Event evt3 = new Event(1000);
//            map1.addEvent(1, 0, evt2);
//            map1.addEvent(0, 1, evt);
//            map1.addEvent(9, 9, evt3);
//            ImageIcon icon = new ImageIcon(sword);
//            Items item = new Items("Wall","Wolf",icon,true);
//            item.addStats(new JLabel("Damage: 50"), Items.DAMAGE, 50);
//            Event evt4 = new Event(new Point(5,2),item);
//            evt4.addEventCount(1);
//            map1.addEvent(0, 9, evt4);
//            Enemie e;
//            
//            map1.build();
//            for (int i = 0; i < 5; i++) {
//                e = new Enemie(Enemie.RANDOMMOVE);
//                map1.addEnemy(e, 50+i*50, 50+i*50);
//            }
//            
//            //Enemie e = new Enemie(1000/240, 100, 100, new Point(0,0),map1.getSpots());
//            //map1.addEnemy(e, 100, 100);
////            Player p = new Player();
////            map1.add(p);
//            
//        } catch (IOException ex) {
//            Logger.getLogger(Tested.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        map1.build();
        map1.requestFocus();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        //map1 = new Game.Map();
        HUT = new javax.swing.JPanel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
         addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }

            
        });
        map1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });
        Action doNothing = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menu();
            }
        };

        HUT.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke("ESCAPE"),
                            "type");

        HUT.getActionMap().put("type",
                             doNothing);
        
        map1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                map1MousePressed(evt);
            }
        });
        
        this.add(HUT);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(HUT);
        HUT.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.PREFERRED_SIZE, 407, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>                        

    private void formKeyTyped(java.awt.event.KeyEvent evt) {                              
      
      if (!move) {
          //System.out.println("FORM");
        move  = true;
          //System.out.println("Work");
       switch (evt.getKeyChar()) {
            case 'a': map1.getplayer().move(3);break;
            case 'd': map1.getplayer().move(2);break;
            case 'w': map1.getplayer().move(1);break;
            case 's': map1.getplayer().move(0);break;
            case 'z': break;
            case 'x':
                map1.play(false);
                Serialize.xStreamOut(map1, "C:\\Users\\f.harz\\Desktop\\player.sh");break;
            case KeyEvent.VK_SPACE: map1.play(false);   ;break;
        }
      }
    }                             
    private void formComponentResized(ComponentEvent evt) {
        if (i != null)
            i.setBounds(0, 0, this.getWidth(), this.getHeight()); 
    }
    private void formKeyReleased(java.awt.event.KeyEvent evt) {                                 
        //System.out.println("RIGHT");
        map1.getplayer().stop();
        move = false;
    }                                

    private void formKeyPressed(java.awt.event.KeyEvent evt) {                                

    }                               
    
    private void map1MousePressed(java.awt.event.MouseEvent evt) {                                  
        map1.playerShoot(); 
    }  
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tested.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tested.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tested.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tested.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Tested t = Serialize.xStreamIn(Tested.class, "C:\\Users\\f.harz\\Desktop\\player.sh");
                //t.setVisible(true);
                new Tested().setVisible(true);
                
            }
        });
    }
    
    public void test() {
        Thread t = new Thread() {
            @Override
            public void run() {
                synchronized(this) {
                try {
                    while (true) {
                        int t = java.lang.Thread.activeCount();
                        Thread.sleep(1000);
                        System.out.println(t);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
                }   
            }
        };
        t.start();
    }

    // Variables declaration - do not modify                     
    private Game.Map map1;
    private javax.swing.JPanel HUT;
    // End of variables declaration                   

   private void menu() {
       if (i == null) {
           i = map1.getInventory();
           i.setVisible(false);
           HUT.add(i);
           i.setBounds(0, 0, this.getWidth(), this.getHeight());
           i.setBorder(BorderFactory.createEtchedBorder());
           i.requestFocus();
        }
       
       System.out.println("Menu");
       if (!menu) {
           map1.play(false);
           menu = true;
           map1.setVisible(false);
           i.setVisible(true);
           i.requestFocus();
       }
       else {
           menu = false;
           map1.setVisible(true);
           i.setVisible(false);
           map1.requestFocus();
           map1.play(true);
       }
       //map1.setVisible(false);
       
       
       //i.setVisible(true);
   }
    
    
}