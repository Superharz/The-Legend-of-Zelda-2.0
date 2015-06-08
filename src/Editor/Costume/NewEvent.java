package Editor.Costume;

import Editor.Content;
import Events.Event;
import Inventory.Items;
import Moveable.Enemies.Enemie;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author f.harz
 */
public class NewEvent extends javax.swing.JDialog {

    Content<Items> items;
    Content<Enemie> enemies;
    private boolean limitation;
    private Point destiny;
    private String name, text, description, mapName;
    private int type, heal, count;
    private LinkedList<String> stats;
    Event event;
    Items item;
    Enemie enemie;
    ImageIcon img;

    /**
     * Creates new form NewSpot
     */
    public NewEvent(java.awt.Frame parent, boolean modal, Content<Items> items, Content<Enemie> enemies) {
        super(parent, modal);
        initComponents();
        this.items = items;
        this.enemies = enemies;
        stats = new LinkedList<String>();
        name = "None";
        limitation = false;
        type = Event.TELEPORT;
        text = "";
        mapName = "";
        heal = 0;
        item = null;
        enemie = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        CountLabel = new javax.swing.JLabel();
        Count = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        Image = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        Info = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Description:");
        jTextArea1.setToolTipText("Description");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Event Creation");

        CountLabel.setText("Event-Count:");

        Count.setText("Count");
        Count.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                CountFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                CountFocusLost(evt);
            }
        });
        Count.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CountKeyReleased(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButton2.setText("DONE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("CANCEL");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Parameters");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Teleport", "Text", "Heal", "Item", "Enemie (Spawn)", "Map-Teleport" }));
        jComboBox1.setToolTipText("Select a Type");
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Limitation");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jSeparator4.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        Info.setEditable(false);
        Info.setColumns(20);
        Info.setRows(5);
        Info.setFocusable(false);
        jScrollPane1.setViewportView(Info);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Count, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Image, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jSeparator1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Count, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE))
                    .addComponent(Image, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(65, 65, 65))
        );

        CountLabel.setVisible(false);
        Count.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CountFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CountFocusGained
        Count.setText("");
    }//GEN-LAST:event_CountFocusGained

    private void CountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_CountFocusLost
        Count.setText("Count:   " + count);
    }//GEN-LAST:event_CountFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        createEvent();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (type == jComboBox1.getSelectedIndex()) {
            return;
        }
        type = jComboBox1.getSelectedIndex();
        Image.setIcon(null);
        Info.setText("");
        System.out.println("Type: " + type);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String str;
        String[] lines;
        String[] line;
        int x = -1, y = -1;
        int choosed;
        switch (type) {
            case Event.TELEPORT:
                str = "Set Coordinates (In spots): \nX: \nY: ";

                destiny = destination(str);
                Info.setText("Teleport: \n  X: " + destiny.x + "\n  Y: " + destiny.y);
                System.out.println(destiny);
                ;
                break;
            case Event.MAPTELEPORT:
                str = "Set Coordinates (In spots): \nX: \nY: \nSet MapName: \nMapName: ";
                jTextArea1.setText(str);
                JOptionPane.showMessageDialog(null, jTextArea1, "Parameters", JOptionPane.PLAIN_MESSAGE);
                str = jTextArea1.getText();
                lines = str.split("\n");
                x = -1;
                y = -1;
                for (int i = 1; i < 3; i++) {
                    line = lines[i].split(" ");
                    String l = line[1].trim();
                    if (check(l)) {
                        if (i == 1) {
                            x = Integer.parseInt(l);
                        } else {
                            y = Integer.parseInt(l);
                        }
                    }
                }
                destiny = new Point(x, y);
                str = lines[4].replace("MapName: ", "");
                mapName = str.trim();
                Info.setText("Teleport: \n  X: " + destiny.x + "\n  Y: " + destiny.y
                        + "\nMapName: \n  " + mapName);
                System.out.println(destiny + " | " + mapName);
                ;
                break;
            case Event.SPAWN:
                choosed = JOptionPane.showConfirmDialog(null, enemies, "Choose an Enemie", JOptionPane.OK_CANCEL_OPTION);
                if (choosed != 0) {
                    return;
                }
                enemie = enemies.selected;
                Image.setIcon(enemie.getImageIcon());
                str = "Set Coordinates (In spots): \nX: \nY: ";
                destiny = destination(str);
                Info.setText("Spawn at: \n  X: " + destiny.x + "\n  Y: " + destiny.y);
                ;
                break;
            case Event.ITEM:
                choosed = JOptionPane.showConfirmDialog(null, items, "Choose an Item", JOptionPane.OK_CANCEL_OPTION);
                if (choosed != 0) {
                    return;
                }
                item = items.selected;
                Image.setIcon(item.getImageIcon());
                str = "Set Coordinates (In spots): \nX: \nY: ";
                destiny = destination(str);
                Info.setText("Spawn at: \n  X: " + destiny.x + "\n  Y: " + destiny.y);
                ;
                break;
            case Event.HEAL:
                str = "Set Heal Amount (Integer): \nAmount: ";
                jTextArea1.setText(str);
                JOptionPane.showMessageDialog(null, jTextArea1, "Parameters", JOptionPane.PLAIN_MESSAGE);
                str = jTextArea1.getText();
                lines = str.split("\n");
                line = lines[1].split(" ");
                String l = line[1].trim();
                if (check(l)) {
                    heal = Integer.parseInt(l);
                }
                Info.setText("Heal: \n  Heal Amount: " + heal);
                System.out.println("Heal: " + heal);
                ;
                break;
            case Event.TEXT:
                str = "Set Text: \n";
                jTextArea1.setText(str);
                JOptionPane.showMessageDialog(null, jTextArea1, "Parameters", JOptionPane.PLAIN_MESSAGE);
                str = jTextArea1.getText();
                text = str.replace("Set Text: \n", "");
                Info.setText("Text: \n" + text);
                System.out.println(text);
                ;
                break;
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        limitation = !limitation;
        if (limitation) {
            Count.setVisible(true);
            CountLabel.setVisible(true);
        } else {
            Count.setVisible(false);
            CountLabel.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void CountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CountKeyReleased
        String number = Count.getText();
        if (number.equals("")) {
            return;
        }
        if (check(number)) {
            count = Integer.parseInt(number);
            System.out.println("Count: " + count);
        } else {
            Count.setText("");
        }
    }//GEN-LAST:event_CountKeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Count;
    private javax.swing.JLabel CountLabel;
    private javax.swing.JLabel Image;
    private javax.swing.JTextArea Info;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

    private Point destination(String description) {
        String str;
        String[] lines;
        String[] line;
        int x = -1, y = -1;
        str = description;
        jTextArea1.setText(str);
        JOptionPane.showMessageDialog(null, jTextArea1, "Parameters", JOptionPane.PLAIN_MESSAGE);
        str = jTextArea1.getText();
        lines = str.split("\n");
        for (int i = 1; i < 3; i++) {
            System.out.println(lines[i]);
            line = lines[i].split(" ");
            String l = line[1].trim();
            System.out.println(l);
            if (check(l)) {
                if (i == 1) {
                    x = Integer.parseInt(l);
                } else {
                    y = Integer.parseInt(l);
                }
            }
        }
        return new Point(x, y);
    }

    private boolean check(String t) {
        if (t.equals("")) {
            return false;
        }
        try {
            Integer.parseInt(t);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private void createEvent() {
        switch (type) {
            case Event.TELEPORT:
                event = new Event(destiny);
                break;
            case Event.MAPTELEPORT:
                event = new Event(destiny, mapName);
                break;
            case Event.SPAWN:
                event = new Event(destiny, enemie);
                break;
            case Event.ITEM:
                event = new Event(destiny, item);
                break;
            case Event.HEAL:
                event = new Event(heal);
                break;
            case Event.TEXT:
                event = new Event(text);
                break;
        }
        if (limitation) {
            event.addEventCount(count);
        }
        items = null;
        enemies = null;
        this.setVisible(false);
    }

    public Event getEvent() {
        return event;
    }
}