package Editor;

import Game.Image;
import IOUtil.Serialize;
import java.awt.Color;
import java.io.File;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is an Generic-Class to contain the different objects that may be add to
 * the Game-Map it also adds function to Save, Delete, Create and Load these
 * objects
 *
 * @author Florian Harz
 */
public class Content<T extends Image> extends JPanel {

    HashMap<JLabel, T> content;
    public T selected;
    JLabel selectLabel;
    /**
     * Creates a new Content-Object
     */
    public Content() {
        content = new HashMap<JLabel, T>();
    }
    /**
     * Adds the given Game-Object to the Content-Data and displays it
     * @param data The Game-Object to be added
     */
    public void add(T data) {
        JLabel l = new JLabel(data.getImageIcon());
        content.put(l, data);

        l.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ContentMousePressed(evt);
            }
        });
        l.setSize(l.getIcon().getIconWidth(), l.getIcon().getIconHeight());

        this.add(l);
        update();
        System.out.println("Worked");
        l.setVisible(true);
    }
    /**
     * Updates the Graphics of the Content-UI
     */
    private void update() {
        this.validate();
        this.repaint();
    }
    /**
     * Sets the Content-Method to erase which will cause the Map to remove 
     * instead of add a Game-Object
     */
    public void erase() {
        if (selectLabel != null) {
            selectLabel.setBorder(null);
        }
        selected = null;
    }
    /**
     * Static save Method to save the given Game-Object at a predefined Location
     * Provides Collision-Detection to prevent unintended overwrite 
     * @param help The Game-Object to save
     * @param gameName The Game-Name of the actual Game, empty if the 
     * Game-Object is NOT a Map
     */
    public static void save(Object help, String gameName) {
        boolean again = false;
        do {
            String name = JOptionPane.showInputDialog("Name:");
            File file = new File("Content/" + name + ".she");
            if (gameName != null) {
                file = new File("Games/" + gameName + "/Original/" + name + ".she");
            }
            if (file.exists()) {
                int remove = JOptionPane.showConfirmDialog(null, name + " already exists! \n Remove?", "Collision detected!", JOptionPane.YES_NO_CANCEL_OPTION);
                if (remove == 2) {
                    return;
                }
                if (remove == 0) {
                    again = false;
                }
                if (remove == 1) {
                    again = true;
                }
            }
            if (!again) {
                Serialize.xStreamOut(help, file.getAbsolutePath());
            }
        } while (again);
    }
    /**
     * Sets the Content-Method to the selected Game-Object
     * Will put a yellow border around that object and removes any other border
     * Lets you save or delete the Game-Object by making a Right-Click on it
     * @param evt The Mouse-Event to get the Mouse-Button and the Game-Object
     */
    private void ContentMousePressed(java.awt.event.MouseEvent evt) {
        JLabel l = (JLabel) evt.getComponent();
        T help = content.get(l);
        if (evt.getButton() == 3) {
            String[] options = {"Save", "Cancel", "Delete"};
            int operation = JOptionPane.showOptionDialog(null, "Save or Delete", "Item Menu", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 1);
            if (operation == 0) {
                save(help, null);
            }
            if (operation == 2) {
                content.remove(l);
                this.remove(l);
                update();
                System.out.println(l.getText() + " removed!");
            }
        } else {
            if (selected != null && selected != help) {
                selectLabel.setBorder(null);
            }
            selected = content.get(l);
            selectLabel = l;
            l.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.yellow));
        }
    }
    /**
     * Gives you the selected Game-Object or null if erase is selected
     * @return The selected Game-Object or null if erase is selected
     */
    public T getContent() {
        return selected;
    }
    /**
     * Return a clone of this Object
     * @return A clone of this Object
     */
    @Override
    public Content clone() {
        Content c = new Content<T>();
        if (content.isEmpty()) {
            return c;
        }
        T[] values = null;
        values = content.values().toArray(values);
        for (int i = 0; i < values.length; i++) {
            c.add(values[i]);
        }
        return c;
    }
}
