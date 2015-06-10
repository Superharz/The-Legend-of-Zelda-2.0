package Game;

import Inventory.Inventory;
import Events.Event;
import IOUtil.Serialize;
import Inventory.Items;
import Moveable.Enemies.Enemie;
import Moveable.Player.Player;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * This is the main Engine It contains the actual Map and the Inventory of the
 * PLayer It is responsible for all Key-Inputs and for the Save/Load of Maps
 *
 * @author Florian Harz
 */
public class Engine extends javax.swing.JFrame implements MapChange {

    boolean move = false;
    Inventory i;
    boolean menu = false;
    String currentMap;
    File original, save;

    /**
     * Creates new form Engine
     */
    public Engine() {
        map1 = new Game.Map();
        initComponents();
        try {
            BufferedImage before = ImageIO.read(this.getClass().
                    getResource("/Pictures/tile1.png"));
            BufferedImage layer2 = ImageIO.read(this.getClass().
                    getResource("/Pictures/tile3.png"));
            BufferedImage plant = ImageIO.read(this.getClass().
                    getResource("/Pictures/plant.png"));
            BufferedImage before2 = ImageIO.read(this.getClass().
                    getResource("/Pictures/tile2.png"));
            BufferedImage sword = ImageIO.read(this.getClass().
                    getResource("/Pictures/sword4.png"));
            Spot s = new Spot(before, false);
            Spot stair = new Spot(plant, -1);
            Spot stair2 = new Spot(plant, -2);
            Spot l1 = new Spot(layer2, 1);
            Spot l2 = new Spot(layer2, 2);
            map1.setUP(10, 10, 5, 5);
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    Spot a = new Spot(before2, true);
                    map1.addSpot(a, i, j);
                }
            }
            map1.addSpot(s, 0, 0);
            map1.addSpot(s, 1, 1);
            map1.addSpot(s, 8, 8);
            map1.addSpot(s, 7, 8);
            map1.addSpot(s, 8, 6);
            map1.addSpot(s, 7, 6);
            map1.addSpot(s, 9, 0);
            map1.addSpot(l1, 8, 1);
            map1.addSpot(l1, 7, 1);
            map1.addSpot(l1, 6, 1);
            map1.addSpot(l1, 5, 1);
            map1.addSpot(stair, 4, 1);
            map1.addSpot(stair2, 8, 2);
            map1.addSpot(l2, 8, 3);
            map1.addSpot(l2, 7, 3);
            map1.addSpot(l2, 6, 3);
            map1.addSpot(l2, 5, 3);
            Event evt = new Event(new Point(5, 1));
            Enemie e2 = new Enemie(Enemie.WALLMOVE);
            Event evt2 = new Event(new Point(2, 5), e2);
            Event evt3 = new Event(1000);
            map1.addEvent(1, 0, evt2);
            map1.addEvent(0, 1, evt);
            map1.addEvent(9, 9, evt3);
            ImageIcon icon = new ImageIcon(sword);
            Items item = new Items("Wall", "Wolf", icon, true);
            item.addStats(new JLabel("Damage: 50"), Items.DAMAGE, 50);
            Event evt4 = new Event(new Point(5, 2), item);
            evt4.addEventCount(1);
            map1.addEvent(0, 9, evt4);
            Enemie e = new Enemie(Enemie.RANDOMMOVE);
            map1.build();
            for (int i = 0; i < 5; i++) {
                e = new Enemie(Enemie.RANDOMMOVE);
                map1.addEnemy(e, 50 + i * 50, 50 + i * 50);
            }
        } catch (IOException ex) {
            Logger.getLogger(Engine.class.getName()).log(Level.SEVERE, null, ex);
        }
        map1.requestFocus();
    }

    public Engine(Map map1) {
        this.map1 = map1;
        map1.addListener(this);
        map1.reUpdate();

        initComponents();

        map1.build();
        map1.requestFocus();


    }

    public Engine(Player p, File original, File save) {
        this.original = original;
        this.save = save;
        String name = p.getMapName();
        currentMap = name;
        File f = new File(save.getPath() + "/" + name + ".she");
        if (!f.exists()) {
            f = new File(original.getPath() + "/" + name + ".she");
        }
        map1 = Serialize.xStreamIn(Map.class, f);
        map1.addListener(this);
        map1.reUpdate();
        initComponents();
        map1.build();
        map1.requestFocus();
        map1.setPreferredSize(map1.getDimension());
        this.setSize(map1.getDimension().width + 30, map1.getDimension().height + 100);
        HUT.setSize(map1.getDimension());
        HUT.setPreferredSize(map1.getDimension());
    }

    public Engine(String file) {
        Map temp = Serialize.xStreamIn(Map.class, file);
        map1 = temp;
        map1.addListener(this);
        map1.reUpdate();
        initComponents();
        map1.build();
        map1.requestFocus();
        map1.setPreferredSize(map1.getDimension());
        this.setSize(map1.getDimension().width + 30, map1.getDimension().height + 100);
        HUT.setSize(map1.getDimension());
        HUT.setPreferredSize(map1.getDimension());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {
        HUT = new javax.swing.JPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });
        map1.addKeyListener(new java.awt.event.KeyAdapter() {
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
                .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, javax.swing.GroupLayout.DEFAULT_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(map1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, javax.swing.GroupLayout.DEFAULT_SIZE));

        pack();
    }// </editor-fold>                        

    private void formKeyTyped(java.awt.event.KeyEvent evt) {
        if (!move) {
            move = true;
            switch (evt.getKeyChar()) {
                case 'a'://Left
                    map1.getplayer().move(3);
                    break;
                case 'd'://Right
                    map1.getplayer().move(2);
                    break;
                case 'w'://Up
                    map1.getplayer().move(1);
                    break;
                case 's'://Down
                    map1.getplayer().move(0);
                    break;
                case 'z':
                    break;
                case 'x'://Save Game
                    save();
                    break;
                case KeyEvent.VK_SPACE://Pause Game
                    map1.play(false);
                    break;
            }
        }
    }

    public void save() {
        map1.play(false);
        map1.releaseEvents();
        Serialize.xStreamOut(map1, save.getPath() + "/" + currentMap + ".she");
        map1.addListener(this);
        map1.play(true);
    }

    private void formComponentResized(ComponentEvent evt) {
        if (i != null) {
            i.setBounds(0, 0, this.getWidth(), this.getHeight());
        }
        HUT.setSize(map1.getDimension());
        HUT.repaint();
        map1.setSize(map1.getDimension());
        map1.repaint();
        map1.build();
    }

    private void formKeyReleased(java.awt.event.KeyEvent evt) {
        map1.getplayer().stop();
        move = false;
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
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Engine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Engine().setVisible(true);

            }
        });
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
        } else {
            menu = false;
            map1.setVisible(true);
            i.setVisible(false);
            map1.requestFocus();
            map1.play(true);
        }
    }

    @Override
    public void mapChange(Point destination, String newMap) {
        Player p = map1.getplayer();
        HUT.remove(map1);
        map1.destroy();
        System.gc();
        map1 = null;
        File f = new File(save.getPath() + "/" + newMap + ".she");

        if (!f.exists()) {
            f = new File(original.getPath() + "/" + newMap + ".she");
        }
        Map temp = Serialize.xStreamIn(Map.class, f);
        System.out.println("DONE!");
        map1 = temp;
        map1.setPlayer(p, destination);
        p.setMapName(newMap);
        map1.addListener(this);
        map1.reUpdate();
        secondaryInit();
        map1.build();
        map1.requestFocus();
        formComponentResized(null);
        currentMap = newMap;
    }

    public void secondaryInit() {
        map1.addKeyListener(new java.awt.event.KeyAdapter() {
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
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(HUT);
        HUT.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(map1, javax.swing.GroupLayout.PREFERRED_SIZE, map1.getDimension().width, javax.swing.GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(map1, javax.swing.GroupLayout.PREFERRED_SIZE, map1.getDimension().height, javax.swing.GroupLayout.PREFERRED_SIZE));

        pack();

    }
}