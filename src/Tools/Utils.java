/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Tools;

import Editor.GameMaker;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This Class contains some often used methods 
 * 
 * @author Florian Harz
 */
public class Utils {
    /**
     * Validates that the given String can be converted to an Integer
     * @param t The String for validation
     * @return True if the String can be converted to an Integer
     */
    public static boolean check(String t) {
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
    /**
/**
     * Lets the user choose an Image-File and return these
     * @param selection The message for the User
     * @param description The description for the Data-Type
     * @param extension The extension of the Data-Type
     * @param f The File for the Star-Folder
     * @return The chosen File by the User, null if no File was chosen
     */
    public File getFile(String selection, String description, String extension, File f) {
        JFileChooser chooser;
        if (f == null) {
            URL u = (this.getClass().getResource("/pictures"));
            chooser = new JFileChooser(u.getPath());
        } else {
            chooser = new JFileChooser(f);
        }
        FileFilter filter;
        filter = new FileNameExtensionFilter(description, extension);
        chooser.addChoosableFileFilter(filter);
        chooser.showDialog(null, selection);
        return chooser.getSelectedFile();
    }
    /**
     * Creates an Image and returns it
     * @param f The position of the previous chosen Image, null if this is the
     * first Image
     * @return The Image chosen by the User, null if none was chosen
     */
    public ImageIcon createImage(File f) {
        ImageIcon image;
        try {
            File file = this.getFile("Choose Texture", "PNG-File  .png", "png", f);
            if (file == null) {
                return null;
            }
            image = new ImageIcon(ImageIO.read(file));
            return image;
        } catch (IOException ex) {
            Logger.getLogger(GameMaker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
