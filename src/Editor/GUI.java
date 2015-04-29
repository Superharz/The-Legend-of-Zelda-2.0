/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import Events.Event;
import Game.Spot;
import Game.Tested;
import IOUtil.Serialize;
import Inventory.Items;
import Moveable.Enemies.Enemie;
import Moveable.Events;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Flo
 */
public class GUI extends javax.swing.JFrame {
    //Spot spot;
    //Event evt;
    //Enemie e;
    //Items item;
    public static final int EVENTS = 1, ENEMIES = 2, ITEMS = 3, SPOTS = 0;
    int selection = SPOTS;
    //HashMap<JLabel, Spot> spots;
    //HashMap<JLabel, Event> events;
    //HashMap<JLabel, Enemie> enemies;
    //LinkedList<Items> items;
    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        //spots = new HashMap<JLabel, Spot>();
       // events = new HashMap<JLabel, Event>();
        //enemies = new HashMap<JLabel, Enemie>();
        //items = new LinkedList<Items>();
        setUP();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        Content = new javax.swing.JInternalFrame();
        Tab = new javax.swing.JTabbedPane();
        Spots = new Editor.Content<Spot>();
        jButton5 = new javax.swing.JButton();
        Events = new Editor.Content<Event>();
        jButton1 = new javax.swing.JButton();
        Enemies = new Editor.Content<Enemie>();
        jButton6 = new javax.swing.JButton();
        Items = new Editor.Content<Items>();
        jButton7 = new javax.swing.JButton();
        GUI = new javax.swing.JInternalFrame();
        map1 = new Editor.MapEditor();
        LaunchTab = new javax.swing.JInternalFrame();
        Launch = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        Content.setClosable(true);
        Content.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        Content.setMaximizable(true);
        Content.setResizable(true);
        Content.setTitle("Content");
        Content.setVisible(true);

        Tab.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabStateChanged(evt);
            }
        });

        jButton5.setText("New");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        Spots.add(jButton5);

        Tab.addTab("Spots", Spots);

        jButton1.setText("New");
        Events.add(jButton1);

        Tab.addTab("Events", Events);

        jButton6.setText("New");
        Enemies.add(jButton6);

        Tab.addTab("Enemies", Enemies);

        jButton7.setText("New");
        Items.add(jButton7);

        Tab.addTab("Items", Items);

        javax.swing.GroupLayout ContentLayout = new javax.swing.GroupLayout(Content.getContentPane());
        Content.getContentPane().setLayout(ContentLayout);
        ContentLayout.setHorizontalGroup(
            ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );
        ContentLayout.setVerticalGroup(
            ContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Tab, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
        );

        jDesktopPane1.add(Content);
        Content.setBounds(20, 40, 190, 300);

        GUI.setResizable(true);
        GUI.setTitle("GUI");
        GUI.setVisible(true);

        map1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                map1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                map1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout GUILayout = new javax.swing.GroupLayout(GUI.getContentPane());
        GUI.getContentPane().setLayout(GUILayout);
        GUILayout.setHorizontalGroup(
            GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
        );
        GUILayout.setVerticalGroup(
            GUILayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );

        jDesktopPane1.add(GUI);
        GUI.setBounds(300, 20, 430, 410);

        LaunchTab.setClosable(true);
        LaunchTab.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        LaunchTab.setTitle("LAUNCH");
        LaunchTab.setVisible(true);

        Launch.setBackground(new java.awt.Color(255, 255, 51));
        Launch.setFont(new java.awt.Font("Arial Black", 1, 48)); // NOI18N
        Launch.setForeground(new java.awt.Color(255, 102, 102));
        Launch.setText("!LAUNCH!");
        Launch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Launch.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        Launch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaunchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LaunchTabLayout = new javax.swing.GroupLayout(LaunchTab.getContentPane());
        LaunchTab.getContentPane().setLayout(LaunchTabLayout);
        LaunchTabLayout.setHorizontalGroup(
            LaunchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Launch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        LaunchTabLayout.setVerticalGroup(
            LaunchTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Launch, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
        );

        jDesktopPane1.add(LaunchTab);
        LaunchTab.setBounds(20, 390, 330, 160);

        jMenu1.setText("File");

        jMenuItem2.setText("Open");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem4.setText("Save");
        jMenu1.add(jMenuItem4);

        jMenuItem5.setText("New");
        jMenu1.add(jMenuItem5);

        jMenuItem3.setText("Close");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("View");

        jMenuItem1.setText("Launch");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem1);

        jMenuItem7.setText("Content");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
       Content.setVisible(!Content.isVisible());
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void map1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_map1MouseClicked
        //map1.click(evt,spot);
        System.out.println("Worked");
        mapCklicked(evt);
    }//GEN-LAST:event_map1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String[] options = {"Create new Object","Choose File"};
        int option = JOptionPane.showOptionDialog(null, "How to create the Object?", "Choose Object", 0, JOptionPane.QUESTION_MESSAGE, null, options,0 );
        if (option != JOptionPane.CLOSED_OPTION) {
            if(option == 0) {

            }
            else {
                Spot s= getObject(Spot.class, "Choose spot");
                if (s == null) return;
                Spots.add(s);
                
                //                JLabel l = new JLabel(s.getIcon());
                //                spots.put(l,s);
                //
                //                l.addMouseListener(new java.awt.event.MouseAdapter() {
                    //                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        //                        SpotMousePressed(evt);
                        //                    }
                    //                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        //                        //ItemMouseEntered(evt);
                        //                        //p.show();
                        //                    }
                    //                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        //                        //ItemMouseExited(evt);
                        //                        //p.show();
                        //                    }
                    //                });
            //
            //                //table.put(l, item);
            //                l.setSize(l.getIcon().getIconWidth(),l.getIcon().getIconHeight()) ;
            //
            //                Spots.add(l);
            //                update();
            //                System.out.println("Worked");
            //                l.setVisible(true);

        }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void TabStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabStateChanged
        selection = Tab.getSelectedIndex();
        System.out.println(selection);
    }//GEN-LAST:event_TabStateChanged

    private void map1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_map1MousePressed
        map1MouseClicked(evt);
    }//GEN-LAST:event_map1MousePressed

    private void LaunchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaunchActionPerformed
        launch();
    }//GEN-LAST:event_LaunchActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        LaunchTab.setVisible(!LaunchTab.isVisible());
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        close();
    }//GEN-LAST:event_formWindowClosing

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
    private javax.swing.JInternalFrame Content;
    private Editor.Content<Enemie> Enemies;
    private Editor.Content<Event> Events;
    private javax.swing.JInternalFrame GUI;
    private Editor.Content<Items> Items;
    private javax.swing.JButton Launch;
    private javax.swing.JInternalFrame LaunchTab;
    private Editor.Content<Spot> Spots;
    private javax.swing.JTabbedPane Tab;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private Editor.MapEditor map1;
    // End of variables declaration//GEN-END:variables
public final void setUP() {
        try {
            map1.setUP(10, 10, 5, 5);

            BufferedImage before = ImageIO.read (this.getClass().
                getResource("/Pictures/tile1.png"));
            Spot s = new Spot(before, true);
            map1.setAllSpots(s);
            map1.build();
        } catch (IOException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
}
private File getFile(String selection, boolean textFile) {
        //Player player;
        //Map map;
        JFileChooser chooser = new JFileChooser();
        FileFilter filter;
        if (textFile) {
            filter = new FileNameExtensionFilter("Text-Files   .txt", "txt");
        }
        else {
            filter = new FileNameExtensionFilter("Game-File   .she", "she");
        }
        chooser.addChoosableFileFilter(filter);
        
        int choosed = chooser.showDialog(null, selection);
        //if (chooser.getSelectedFile() != null)
        return chooser.getSelectedFile();
        
    }
    public <T> T getObject(Class<T> data, String file) {
        T clazz;
        File f= getFile(file, false);
        if (f == null) return null;
        clazz = Serialize.xStreamIn(data, f);
        return clazz;
    }
    private void update() {
        Spots.validate();
        Spots.repaint();
        map1.validate();
        map1.repaint();
    }
//    private void SpotMousePressed(java.awt.event.MouseEvent evt) {
//        JLabel l = (JLabel)evt.getComponent();
//        Spot help = spots.get(l);
//        if (spot != null && spot != help) {
//            spots.
//            l.setBorder(null);
//        }
//        spot = spots.get(l);   
//        l.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.yellow));
//        System.out.println("HI");
//        
//    //table.get(l).setVisible(true);
//}

    private void mapCklicked(MouseEvent evt) {
        switch (selection) {
            case EVENTS : map1.click(evt, Events.getContent());break;
            case ENEMIES: map1.click(evt, Enemies.getContent());break;
            case ITEMS  : map1.click(evt, Items.getContent());break;
            case SPOTS  : map1.click(evt, Spots.getContent());break;
        }
        update();
    }

    private void launch() {
        Serialize.xStreamOut(map1, "D:\\1GB\\The-Legend-of-Zelda-2.0\\Save\\temp.she");
        Tested test = new Tested("D:\\1GB\\The-Legend-of-Zelda-2.0\\Save\\temp.she");
        test.setVisible(true);
        test.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void close() {
        try{
 
    		File file = new File("D:\\1GB\\The-Legend-of-Zelda-2.0\\Save\\temp.she");
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
            System.out.println(e.getMessage());
    	}
    }

}
