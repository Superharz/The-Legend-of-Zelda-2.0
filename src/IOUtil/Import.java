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
import java.awt.Point;
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
    private final String COMMENT = "#", TEXTURE = "@", EVENT = "!", EVENTI = "/", ENEMIE = "%",ENEMIEI = "~", SPOT = "$", ITEM = "^", MAP = "?",SPOTI =  "*";
    HashMap<Integer, BufferedImage> textures;
    HashMap<Integer, Spot> spots;
    HashMap<Integer, Event> events;
    HashMap<Integer, Enemie> enemies;
    HashMap<Integer, Items> items;
    Map map;
    public Import() {
        textures = new HashMap<Integer, BufferedImage>();
        spots    = new HashMap<Integer,          Spot>();
        events   = new HashMap<Integer,         Event>();
        enemies  = new HashMap<Integer,        Enemie>();
        items    = new HashMap<Integer,         Items>();
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
    private void createEnemy(String line) {
        
        
        
        
        
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
        final String VALUE = "V", WALKABLE = "W", HEIGHT = "H", POSITION = "P", NUMBER = "N";
        int value = -1, layer = 0, number = -1, x = 0, y = 0;
        boolean walkable = false;
        String[] sub;
        String[] info = line.split(" ");
        for (int i = 0; i < info.length; i++) {
            if (info[i].startsWith(VALUE)) {
                sub = info[i].split(":");
                value = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(POSITION)) {
                sub = info[i].split(":");
                x = Integer.parseInt(sub[1]);
                y = Integer.parseInt(sub[2]);
            }
            if (info[i].startsWith(HEIGHT)) {
                sub = info[i].split(":");
                layer = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(NUMBER)) {
                sub = info[i].split(":");
                number = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(WALKABLE)) {
                sub = info[i].split(":");
                walkable = (Integer.parseInt(sub[1]) != 0);
            }
            //System.out.println(info[i]);
        }
        
        if (number == -1 ){
            Spot s = new Spot(textures.get(value), layer);
            if (!walkable)
                s = new Spot(textures.get(value), walkable);
            map.addSpot(s, x, y);
        }
        else{
            map.addSpot(spots.get(number).clone(), x, y);
        }   
    }
    public void createSpot(String line) {
        final String VALUE = "V", WALKABLE = "W", HEIGHT = "H", NUMBER = "N";
        int value = -1, layer = 0, number = -1;
        boolean walkable = false;
        String[] sub;
        String[] info = line.split(" ");
        for (int i = 0; i < info.length; i++) {
            if (info[i].startsWith(VALUE)) {
                sub = info[i].split(":");
                value = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(HEIGHT)) {
                sub = info[i].split(":");
                layer = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(NUMBER)) {
                sub = info[i].split(":");
                number = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(WALKABLE)) {
                sub = info[i].split(":");
                walkable = (Integer.parseInt(sub[1]) != 0);
            }
            //System.out.println(info[i]);
        }
        

        Spot s = new Spot(textures.get(value), layer);
        if (!walkable)
            s = new Spot(textures.get(value), walkable);
        spots.put(number, s);         
    }
    private void buildEvent(String line) {
        final String VALUE = "V", TYPE = "T", POSITION = "P", TELEPORT = "B", NAME = "N", HEAL = "H", TEXT = "S", ENEMIE = "E", AMOUNT = "A", ITEM = "I";
        int value = -1, type = 0, x = -1, x2 = -1, y2 = -1, y = -1, heal = -1, enemie = -1, amount = -1, item = -1;
        String text = "";
        String[] sub;
        String[] info = line.split(" ");
        for (int i = 0; i < info.length; i++) {
            if (info[i].startsWith(TYPE)) {
                sub = info[i].split(":");
                type = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(VALUE)) {
                sub = info[i].split(":");
                value = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(POSITION)) {
                sub = info[i].split(":");
                x = Integer.parseInt(sub[1]);
                y = Integer.parseInt(sub[2]);
            }
            if (info[i].startsWith(TELEPORT)) {
                sub = info[i].split(":");
                x2 = Integer.parseInt(sub[1]);
                y2 = Integer.parseInt(sub[2]);
            }
            if (info[i].startsWith(NAME)) {
                sub = info[i].split(":");
                text = (sub[1]);
            }
            if (info[i].startsWith(HEAL)) {
                sub = info[i].split(":");
                heal = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(ITEM)) {
                sub = info[i].split(":");
                x2 = Integer.parseInt(sub[1]);
                y2 = Integer.parseInt(sub[2]);
                item = Integer.parseInt(sub[3]);
            }
            if (info[i].startsWith(TEXT)) {
                sub = info[i].split(":");
                text = sub[1];
            }
            if (info[i].startsWith(ENEMIE)) {
                sub = info[i].split(":");
                x2 = Integer.parseInt(sub[1]);
                y2 = Integer.parseInt(sub[2]);
                enemie = Integer.parseInt(sub[3]);
            }
            if (info[i].startsWith(AMOUNT)) {
                sub = info[i].split(":");
                amount = Integer.parseInt(sub[1]);
            }
            //System.out.println(info[i]);
        }
        if (value == -1) {
        Event evt = new Event( new Point(x2,y2));
        switch (type) {
            case Event.TELEPORT:
                if(!text.equals(""))
                    evt = new Event(new Point(x2,y2), text);
                break;
            case Event.ITEM:
                evt = new Event(new Point(x2,y2), items.get(item));
                break;
            case Event.SPAWN:       
                evt = new Event(new Point(x2,y2), enemies.get(enemie));
                break;
            case Event.HEAL:        
                evt = new Event(heal);
                break;
            case Event.TEXT:        
                evt = new Event(text);
                break;
        }
        if (amount != -1)
            evt.addEventCount(amount);
        
        }
        else{
            Event evt = events.get(value);
        }
        //events.put(value, evt);
        
        
        
        
    }
    private void createEvent(String line) {
        final String VALUE = "V", TYPE = "T", POSITION = "P", NAME = "N", HEAL = "H", TEXT = "S", ENEMIE = "E", AMOUNT = "A", ITEM = "I";
        int value = -1, type = 0, x = -1, y = -1, heal = -1, enemie = -1, amount = -1, item = -1;
        String text = "";
        String[] sub;
        String[] info = line.split(" ");
        for (int i = 0; i < info.length; i++) {
            if (info[i].startsWith(TYPE)) {
                sub = info[i].split(":");
                type = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(VALUE)) {
                sub = info[i].split(":");
                value = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(POSITION)) {
                sub = info[i].split(":");
                x = Integer.parseInt(sub[1]);
                y = Integer.parseInt(sub[2]);
            }
            if (info[i].startsWith(NAME)) {
                sub = info[i].split(":");
                text = (sub[1]);
            }
            if (info[i].startsWith(HEAL)) {
                sub = info[i].split(":");
                heal = Integer.parseInt(sub[1]);
            }
            if (info[i].startsWith(ITEM)) {
                sub = info[i].split(":");
                x = Integer.parseInt(sub[1]);
                y = Integer.parseInt(sub[2]);
                item = Integer.parseInt(sub[3]);
            }
            if (info[i].startsWith(TEXT)) {
                sub = info[i].split(":");
                text = sub[1];
            }
            if (info[i].startsWith(ENEMIE)) {
                sub = info[i].split(":");
                x = Integer.parseInt(sub[1]);
                y = Integer.parseInt(sub[2]);
                enemie = Integer.parseInt(sub[3]);
            }
            if (info[i].startsWith(AMOUNT)) {
                sub = info[i].split(":");
                amount = Integer.parseInt(sub[1]);
            }
            //System.out.println(info[i]);
        }
        Event evt = new Event( new Point(x,y));
        switch (type) {
            case Event.TELEPORT:
                if(!text.equals(""))
                    evt = new Event(new Point(x,y), text);
                break;
            case Event.ITEM:
                evt = new Event(new Point(x,y), items.get(item));
                break;
            case Event.SPAWN:       
                evt = new Event(new Point(x,y), enemies.get(enemie));
                break;
            case Event.HEAL:        
                evt = new Event(heal);
                break;
            case Event.TEXT:        
                evt = new Event(text);
                break;
        }
        if (amount != -1)
            evt.addEventCount(amount);
        events.put(value, evt);
        
        
 
        
        
        
        
    }
    private void buildItem(String line) {
        
        
        
        
       
    }
    public Map buildMap() {
        getText(getFile("Select a Map"));
        
        
        
        return map;
    }
    public void buildMap(String line) {
        final String VALUE = "V", SIZE = "S", WALKABLE = "W", HEIGHT = "H", PLAYER = "P", NUMBER = "N";
        int value = -1, layer = 0, width = 0, height = 0,x = 0,y = 0, number = -1;
        boolean walkable = false;
        String[] sub;
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
            if (info[i].startsWith(NUMBER)) {
                sub = info[i].split(":");
                number = Integer.parseInt(sub[1]);
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
        if (number == -1 ){
            Spot s = new Spot(textures.get(value), layer);
            if (!walkable)
                s = new Spot(textures.get(value), walkable);
            map.setAllSpots(s);
        }
        else{
            map.setAllSpots(spots.get(number));
        }
        //map.build();
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
                if (line.startsWith(ENEMIEI))
                    createEnemy(line.substring(1));
                if (line.startsWith(EVENT))
                    buildEvent(line.substring(1));
                if (line.startsWith(EVENTI))
                    createEvent(line.substring(1));
                if (line.startsWith(ITEM))
                    buildItem(line.substring(1));
                if (line.startsWith(SPOT))
                    buildSpot(line.substring(1));
                if (line.startsWith(SPOTI))
                    createSpot(line.substring(1));
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
