package Inventory;

import Game.Image;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This is the main Item-Class 
 * @author Florian Harzrian Harz
 */
public class Items extends JLabel implements Image {

    LinkedList<JLabel> stats = new LinkedList<JLabel>();
    int basic[] = new int[4];
    public final static int NONE = -1, LIVE = 0, DAMAGE = 1, ARMOR = 2, SPEED = 3;
    private String type, description;
    private int typeInt;
    private boolean useable;
    private boolean isSelected = false;

    /**
     * Creates new form Item
     */
    public Items(String type, String name, Icon icon, boolean useable) {
        setUp(type, name, icon, useable);
    }

    public Items(int type, String name, Icon icon, boolean useable) {
        setUp(type, name, icon, useable);
    }

    /**
     * Do NOT use except for deSerilazation
     */
    public Items() {
    }

    public JLabel getStat(int index) {
        if (index >= 0 && index < stats.size()) {
            return stats.get(index);
        } else {
            return null;
        }
    }

    public int getLength() {
        return stats.size();
    }

    public void setUp(String type, String name, Icon icon, boolean useable) {
        this.useable = useable;
        this.type = type;
        this.setName(name);
        this.setIcon(icon);
        update();
    }

    public void setUp(int type, String name, Icon icon, boolean useable) {
        this.typeInt = type;
        setUp(type, name, icon, useable);
    }

    public void addStats(JLabel stat, int type, int value) {
        if (type >= 0) {
            basic[type] = value;
        }
        stats.add(stat);
        update();
    }

    public int[] getBasicStats() {
        return basic;
    }

    public void addStats(JLabel stat) {
        stats.add(stat);
        update();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void update() {
        this.validate();
        this.repaint();
    }

    public boolean isUseable() {
        return useable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelection(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public ImageIcon getIcon() {
        return (ImageIcon) super.getIcon();
    }

    @Override
    public ImageIcon getImageIcon() {
        return (ImageIcon) super.getIcon();
    }
}
