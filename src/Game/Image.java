package Game;

import javax.swing.ImageIcon;

/**
 * This is the Interface to ensure the getImageIcon() method for the generic
 * Content-Class
 *
 * @author Florian Harz
 */
public interface Image {
    /**
     * Method to get an ImageIcon for a Game-Object
     * @return The ImageIcon of the Game-Object
     */
    public ImageIcon getImageIcon();
}
