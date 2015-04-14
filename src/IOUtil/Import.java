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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author f.harz
 */
public class Import {
    private final String COMMENT = "#", TEXTURE = "@", EVENT = "!", ENEMIE = "%", SPOT = "$", ITEM = "^", MAP = "?";
    HashMap<Integer, BufferedImage> textures;
    Map map;
    public Import() {
        textures = new HashMap<Integer, BufferedImage>();
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
        final String VALUE = "V", PATH = "P";
        int value = -1;
        String[] sub;
        String f = null;
        String[] info = line.split(" ");
        for (int i = 0; i < info.length; i++) {
            if (info[i].startsWith(VALUE)) {
                sub = info[i].split(":");
                value = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(PATH)) {
                sub = info[i].split(":");
                f = sub[1];
            }
            //System.out.println(info[i]);
        }
        
        if (value != -1 && f != null) {
            try{
                System.out.println(f);
                BufferedImage img = ImageIO.read (this.getClass().
                    getResource(f));
                textures.put(value, img);
                JOptionPane.showMessageDialog(null, "Hi", "Ho", 1, new ImageIcon(img));
            }
            catch(Exception ex) {
                System.out.println(ex.getMessage());
            }
            
            
        }
        System.out.println(value);
        
        
        
       
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
        final String VALUE = "V", SIZE = "S", WALKABLE = "W", HEIGHT = "H", PLAYER = "P";
        int value = -1, layer = 0, width = 0, height = 0,x = 0,y = 0;
        boolean walkable = false;
        String[] sub;
        String f = null;
        String[] info = line.split(" ");
        for (int i = 0; i < info.length; i++) {
            if (info[i].startsWith(VALUE)) {
                sub = info[i].split(":");
                value = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(PLAYER)) {
                sub = info[i].split(":");
                x = Integer.parseInt(sub[1]);
                y = Integer.parseInt(sub[2]);
            }
            if (info[i].startsWith(HEIGHT)) {
                sub = info[i].split(":");
                layer = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(WALKABLE)) {
                sub = info[i].split(":");
                walkable = (Integer.parseInt(sub[1]) != 0);
            }
            if (info[i].startsWith(SIZE)) {
                sub = info[i].split(":");
                width = Integer.parseInt(sub[1]);
                height = Integer.parseInt(sub[2]);
            }
            //System.out.println(info[i]);
        }
        
        map = new Map();
        map.setUP(width, height, x, y);
        Spot s = new Spot(textures.get(value), layer);
        if (!walkable)
            s = new Spot(textures.get(value), walkable);
        map.setAllSpots(s);
        
    }
    
    private void getText(File f) {
        if (f == null) return; 
        ReadWriteTextFileWithEncoding reader = new ReadWriteTextFileWithEncoding(f.getAbsolutePath());
        try {
            LinkedList<String> text = reader.read();
            String line;
            //String info;
            for (int i = 0; i < text.size(); i++) {
                line = text.get(i);
                System.out.println(text.get(i));
                if (line.startsWith(COMMENT))
                    continue;
                
                if (line.startsWith(MAP))
                    buildMap(line.substring(1));
                if (line.startsWith(ENEMIE))
                    buildEnemy(line.substring(1));
                if (line.startsWith(EVENT))
                    buildEvent(line.substring(1));
                if (line.startsWith(ITEM))
                    buildItem(line.substring(1));
                if (line.startsWith(SPOT))
                    buildSpot(line.substring(1));
                if (line.startsWith(TEXTURE))
                    buildTexture(line.substring(1));
                
                
                
                
                
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
