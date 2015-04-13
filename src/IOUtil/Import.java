/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IOUtil;

import Events.Event;
import Game.Map;
import Game.Spot;
import Inventory.Items;
import Moveable.Enemies.Enemie;
import Moveable.Player.Player;
import Tools.ReadWriteTextFileWithEncoding;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author f.harz
 */
public class Import {
    private final String COMMENT = "#", TEXTURE = "@", EVENT = "!", ENEMIE = "%", SPOT = "$", ITEM = "^", MAP = "?";
    
    
    public Import() {
        
    }
    private File getFile(String selection) {
        Player player;
        Map map;
        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("Text-Files   .txt", "txt");

        chooser.addChoosableFileFilter(filter);
        
        int choosed = chooser.showDialog(null, selection);
        //if (chooser.getSelectedFile() != null)
        return chooser.getSelectedFile();
        
    }
    private void buildEnemy(String line) {
        
        
        
        
        
    }
    private void buildTexture(String line) {
        
        
        
        
       
    }
    public Player buildPlayer(String line) {
        
        
        
        
       return null;
    }
    private void buildSpot(String line) {
        
        
        
        
        
    }
    private void buildEvent(String line) {
        
        
        
        
        
    }
    private void buildItem(String line) {
        
        
        
        
       
    }
    public Map buildMap() {
        getText(getFile("Select a Map"));
        
        
        
        return null;
    }
    public void buildMap(String line) {
        
        
    }
    
    private void getText(File f) {
        if (f == null) return; 
        ReadWriteTextFileWithEncoding reader = new ReadWriteTextFileWithEncoding(f.getAbsolutePath());
        try {
            LinkedList<String> text = reader.read();
            String line;
            for (int i = 0; i < text.size(); i++) {
                line = text.get(i);
                System.out.println(text.get(i));
                if (line.startsWith(COMMENT))
                    continue;
                if (line.startsWith(MAP))
                    buildMap(line);
                if (line.startsWith(ENEMIE))
                    buildEnemy(line);
                if (line.startsWith(EVENT))
                    buildEvent(line);
                if (line.startsWith(ITEM))
                    buildItem(line);
                if (line.startsWith(SPOT))
                    buildSpot(line);
                if (line.startsWith(TEXTURE))
                    buildSpot(line);
                
                
                
                
                
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(Import.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
    }

    @Override
    public String toString() {
        return null;
    }
    public boolean equals(Import object) {
        return false;
    }
    public void destroy() {

    }
    @Override
    public Import clone() {
        return null;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

                new Import().buildMap();

    }
}
