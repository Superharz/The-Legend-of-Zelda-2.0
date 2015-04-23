/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.JLabel;

/**
 *
 * @author f.harz
 */
public class Items extends JLabel {
    LinkedList<JLabel> stats = new LinkedList<JLabel>();
    int basic[] = new int[4];
    public final static int LIVE = 0, DAMAGE = 1, ARMOR = 2, SPEED = 3; 
    private String type;
    private boolean useable;
    private boolean isSelected = false;

    /**
     * Creates new form Item
     */
    public Items(String type,String name, Icon icon,boolean useable) {
        setUp(type, name, icon,useable);
    }
    /**
     * Do NOT use except for deSerilazation
     */
    public Items() {

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
    
    public void setUp(String type,String name, Icon icon,boolean useable) {
        this.useable = useable;
        this.type = type;
        this.setName(name);
        this.setIcon(icon);
        update();
    }
    public void addStats(JLabel stat,int type,int value){
        if (type >= 0) {
            basic[type] = value;
        }
        stats.add(stat);
        update();
    }
    public int[] getBasicStats(){
        return basic;
    }
    public void addStats(JLabel stat){
        stats.add(stat);
        update();
    }
    private void update() {
        this.validate();
        this.repaint();
    }
    public boolean isUseable(){
        return useable;
    }
    public boolean isSelected() {
        return isSelected;
    }
    public void setSelection(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
