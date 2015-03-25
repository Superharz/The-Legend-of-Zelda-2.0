/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

/**
 *
 * @author f.harz
 */
public class Items extends JLabel {
    LinkedList<JLabel> stats = new LinkedList<JLabel>();
    private String type;

    /**
     * Creates new form Item
     */
    public Items(String type,String name, Icon icon) {
        setUp(type, name, icon);
    }

    
                   




    // Variables declaration - do not modify                     
    

    
    public JLabel getStat(int index) {
        if (index >= 0 && index < stats.size())
            return stats.get(index);
        else return null;
    }
    
    public int getLength() {
        return stats.size();
    }
    
    public void setUp(String type,String name, Icon icon) {
        this.type = type;
        this.setName(name);
        this.setIcon(icon);
        update();
    }
    public void addStats(JLabel stat){
        stats.add(stat);
        update();
    }
    private void update() {
        this.validate();
        this.repaint();
    }

}
