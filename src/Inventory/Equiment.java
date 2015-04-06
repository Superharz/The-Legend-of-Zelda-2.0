/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import Moveable.Events;
import java.awt.Color;
import java.awt.PopupMenu;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;

/**
 *
 * @author f.harz
 */
public class Equiment extends javax.swing.JPanel {
    public final List<Events> listeners = new ArrayList<Events>();
    LinkedList<Items> items = new LinkedList<Items>();
    Items selection;
    ///Hashtable<JLabel, Items> table = new Hashtable<JLabel,Items >();
    /**
     * Creates new form Equiment
     */
    public Equiment() {
        try {
            initComponents();
            BufferedImage before = ImageIO.read (this.getClass().
                        getResource("/Pictures/tile1.png"));
            ImageIcon icon = new ImageIcon(before);
            Items i = new Items("Wall","Wall", icon,false);
            i.addStats(new JLabel("Damage:  100     ")) ;
            i.addStats(new JLabel("Damage:  50      ")) ;
            i.addStats(new JLabel("Damage:  20      ")) ;
            i.addStats(new JLabel("Damage:  10      ")) ;
            
            this.add(i);
        } catch (IOException ex) {
            Logger.getLogger(Equiment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Equiment = new javax.swing.JPanel();
        Stats = new javax.swing.JPanel();
        Image = new javax.swing.JLabel();
        Name = new javax.swing.JLabel();
        Info = new javax.swing.JPanel();

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Equiment");

        Equiment.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                EquimentMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EquimentMousePressed(evt);
            }
        });

        javax.swing.GroupLayout EquimentLayout = new javax.swing.GroupLayout(Equiment);
        Equiment.setLayout(EquimentLayout);
        EquimentLayout.setHorizontalGroup(
            EquimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );
        EquimentLayout.setVerticalGroup(
            EquimentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 117, Short.MAX_VALUE)
        );

        Name.setText("Name:");

        javax.swing.GroupLayout StatsLayout = new javax.swing.GroupLayout(Stats);
        Stats.setLayout(StatsLayout);
        StatsLayout.setHorizontalGroup(
            StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Name, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        StatsLayout.setVerticalGroup(
            StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(StatsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(StatsLayout.createSequentialGroup()
                        .addComponent(Name)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Stats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Equiment, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Equiment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Stats, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void EquimentMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EquimentMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_EquimentMousePressed

    private void EquimentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EquimentMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_EquimentMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Equiment;
    private javax.swing.JLabel Image;
    private javax.swing.JPanel Info;
    private javax.swing.JLabel Name;
    private javax.swing.JPanel Stats;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

public void add(Items item) {
    items.add(item);
    //JLabel l = new JLabel(item.getIcon());
    
    //l.add(p) ;
    item.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mousePressed(java.awt.event.MouseEvent evt) {
            ItemMousePressed(evt);
        }
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            ItemMouseEntered(evt);
            //p.show();
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            ItemMouseExited(evt);
            //p.show();
        }
    });
    
    //table.put(l, item);
    item.setSize(item.getIcon().getIconWidth(),item.getIcon().getIconHeight() ) ;
    Equiment.add(item);
    update();
    System.out.println("Worked");
    item.setVisible(true);
}

private void ItemMouseEntered(java.awt.event.MouseEvent evt) {
    Items l = (Items)evt.getComponent();
    if (!l.equals(selection)) {
        reset();
    }
    showInfo(l);
}

private void ItemMouseExited(java.awt.event.MouseEvent evt) {
    Items l = (Items)evt.getComponent();
    if (!l.equals(selection)) {
        
        reset();
        if (selection != null)
            showInfo(selection);
    }
    //table.get(l).setVisible(true);
}
private void ItemMousePressed(java.awt.event.MouseEvent evt) {
    Items l = (Items)evt.getComponent();
    if (!l.equals(selection)) {
        selection = l;
        reset();
        showInfo(l);
    }
    if(evt.getButton()==3 && l.isUseable()) {
        for (Events hl : listeners) {
            hl.use(l);
        }
    }
    
    //table.get(l).setVisible(true);
}
private void showInfo(Items l) {
    //Items l = (Items)evt.getComponent();
    Image.setIcon(l.getIcon());
    Name.setText("Name: "+l.getName()) ;
    JLabel stat;
    for (int i = 0; i < l.getLength(); i++) {
        stat = l.getStat(i);
        //stat.setBounds(0, -20, 100, 100);
        Info.setBackground(Color.red);
        Info.add(stat); 
    }
    update();
}
private void update() {
        Info.validate();
        Info.repaint();
    }
    public void addListener(Events toAdd) {
        //listeners.add(toAdd);
        listeners.add(toAdd);
    }
    private void reset() {
        Image.setIcon(null);
        Name.setText("Name:");
        Info.removeAll();
        update();
    }
}
