/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import Moveable.Enemies.Enemie;
import Moveable.Player.Player;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Flo
 */
public class GUI extends javax.swing.JFrame {
    boolean move = false;
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        
        try {
            BufferedImage before = ImageIO.read (this.getClass().
                    getResource("/Pictures/tile1.png"));
            BufferedImage before2 = ImageIO.read (this.getClass().
                    getResource("/Pictures/tile2.png"));
            Spot s = new Spot(before, false);
            Spot a = new Spot(before2, true);
            map1.setUP(10, 10,10,10);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map1.addSpot(a, i, j);
                }
            }
            map1.addSpot(s, 0, 0);
            map1.addSpot(s, 1, 1);
            map1.addSpot(s, 8, 8);
            map1.addSpot(s, 7, 8);
            map1.addSpot(s, 8, 6);
            map1.addSpot(s, 7, 6);
            map1.build();
            //Enemie e = new Enemie(1000/240, 100, 100, new Point(0,0),map1.getSpots());
            //map1.addEnemy(e, 100, 100);
//            Player p = new Player();
//            map1.add(p);
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
//        this.validate();
//        this.repaint();
//        this.update(this.getGraphics());
//        this.setVisible(false);
//        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        map1 = new Game.Map();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
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

        map1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                map1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
      
      if (!move) {
          System.out.println("FORM");
        move  = true;
       switch (evt.getKeyChar()) {
            case 'a': map1.getplayer().move(3);break;
            case 'd': map1.getplayer().move(2);break;
            case 'w': map1.getplayer().move(1);break;
            case 's': map1.getplayer().move(0);break;
        }
      }
    }//GEN-LAST:event_formKeyTyped

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        System.out.println("RIGHT");
        map1.getplayer().stop();
        move = false;
    }//GEN-LAST:event_formKeyReleased

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void map1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_map1MousePressed
        System.out.println("Works");
    }//GEN-LAST:event_map1MousePressed

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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Game.Map map1;
    // End of variables declaration//GEN-END:variables

   
    
    
}
