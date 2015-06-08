package Menu;

import Game.Engine;
import IOUtil.Serialize;
import Moveable.Player.Player;
import Tools.ReadWriteTextFileWithEncoding;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author f.harz
 */
public class Game {

    public String gameName;
    String mainMap;
    File playerFile;
    HashMap<String, File> players;
    File directory;

    public Game(File directory) {
        try {
            this.directory = directory;
            File ini = new File(directory.getPath() + "/game.ini");
            System.out.println(ini.toString());
            ReadWriteTextFileWithEncoding r = new ReadWriteTextFileWithEncoding(ini.getAbsolutePath());
            String[] lines = new String[0];
            lines = r.read().toArray(lines);
            gameName = lines[0];
            mainMap = lines[1];
            String f = ini.getParent();
            playerFile = new File(f + "/Players/");
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Game(String ini) {
        this(new File(ini));
    }

    public void setPlayers() {
        players = new HashMap<String, File>();
        File[] files = playerFile.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                players.put(files[i].getName(), files[i]);
            }

        }
    }

    public void newPlayer(String name) {
        File f = new File(playerFile.getPath() + "/" + name + "/Save");
        if (f.exists()) {
            int i = JOptionPane.showConfirmDialog(null, "Player " + name + " already exists."
                    + "\n Overide?", "Collision detected!", JOptionPane.YES_NO_OPTION);
            if (i == 1) {
                return;
            }
        }
        f.mkdirs();
        f = new File(playerFile.getPath() + "/" + name);
        System.out.println(f);
        players.put(name, f);
        setPlayers();
    }

    @Override
    public String toString() {
        return null;
    }

    public boolean equals(Game object) {
        return false;
    }

    public void destroy() {
    }

    @Override
    public Game clone() {
        return null;
    }

    void delete(String player) {
        File f = players.get(player);
        f.delete();
        if (f.exists()) {
            deleteFolder(f);
        }
        System.out.println(f.exists());
        setPlayers();
    }

    private void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if (files != null) { //some JVMs return null for empty dirs
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

    public Engine startGame(String dir) {
        File f = players.get(dir);
        File player = new File(f.getPath() + "/player.she");
        System.out.println(player);
        Player p = null;
        if (!player.exists()) {
            p = new Player();
            p.setMapName(mainMap);
            Serialize.xStreamOut(p, player);
        } else {
            p = Serialize.xStreamIn(Player.class, player);
        }
        if (p != null) {
            File original = new File(directory.getPath() + "/Original");
            File save = new File(f.getPath() + "/Save");
            Engine t = new Engine(p, original, save);
            t.setVisible(true);
            return t;
        }
        return null;
    }
}
