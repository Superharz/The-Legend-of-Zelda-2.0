package Inventory;

import Game.Image;
import java.util.LinkedList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * This is the main Item-Class 
 * @author Florian Harz
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
     * Creates a new Item
     * @param type The Type-Name of the Item
     * @param name The Name of the Item
     * @param icon The Icon of the Item
     * @param useable True if the Item can be Equip
     */
    public Items(String type, String name, Icon icon, boolean useable) {
        this.useable = useable;
        this.type = type;
        this.setName(name);
        this.setIcon(icon);
        update();
    }
    /**
     * Creates a new Item
     * @param type The Type-Integer of the Item
     * @param name The Name of the Item
     * @param icon The Icon of the Item
     * @param useable True if the Item can be Equip
     */
    public Items(int type, String name, Icon icon, boolean useable) {
        this("", name, icon, useable);
        this.typeInt = type;
    }

    /**
     * Do NOT use except for deSerilazation
     */
    @Deprecated
    public Items() {
    }
    /**
     * Returns the Stat at a given Index as a JLabel
     * @param index The Index of the Stat in the LinkedList
     * @return A JLabel with the Stat, null if the index is Out-Of-Range
     */
    public JLabel getStat(int index) {
        if (index >= 0 && index < stats.size()) {
            return stats.get(index);
        } else {
            return null;
        }
    }
    /**
     * Gets the Length of the LinkedList containing the Stats
     * @return The Amount of Stats in the LinkedList
     */
    public int getLength() {    
        return stats.size();
    }
    /**
     * Adds a Stat to the Item, it can also be a Basic-Stat, set Type to -1 when
     * It's not supposed to be a Basic-Stat
     * @param stat The JLabel containing the Stat
     * @param type The Type-Integer for the Basic-Stat Type
     * @param value The Value for the Basic-Stat
     */
    public void addStats(JLabel stat, int type, int value) {
        if (type >= 0) {
            basic[type] = value;
        }
        stats.add(stat);
        update();
    }
    /**
     * Returns an Integer Array containing the Basic-Stats
     * @return An Integer Array containing the Basic-Stats
     */
    public int[] getBasicStats() {
        return basic;
    }
    /**
     * Adds a Stat to the Item that is not a Basic-Stat
     * @param stat The JLabel containing the Stat
     */
    public void addStats(JLabel stat) {
        addStats(stat, -1, -1);
    }
    /**
     * Sets the Description of the Item
     * @param description The Description for the Item
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Updates the UI-Graphics of this Component
     */
    private void update() {
        this.validate();
        this.repaint();
    }
    /**
     * Whether the Item can be Equip
     * @return True if the Item can be Equip
     */
    public boolean isUseable() {
        return useable;
    }
    /**
     * Whether the Item is selected in the Equipment
     * @return True if the Item is selected in the Equipment
     */
    public boolean isSelected() {
        return isSelected;
    }
    /**
     * Toggles the Selection of this Item
     * @param isSelected True if the Item is Selected in the Equipment
     */
    public void setSelection(boolean isSelected) {
        this.isSelected = isSelected;
    }
    /**
     * Gets the Icon of this Item
     * @return The ImageIcon of this Item
     */
    @Override
    public ImageIcon getIcon() {
        return getImageIcon();
    }
    /**
     * Gets the Icon of this Item
     * @return The ImageIcon of this Item
     */
    @Override
    public ImageIcon getImageIcon() {
        return (ImageIcon) super.getIcon();
    }
}
