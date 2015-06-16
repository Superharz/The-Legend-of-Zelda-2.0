package Editor;

import java.io.File;
import java.util.Collections;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Display a file system in a JTree view
 *
 * @version $Id: FileTree.java,v 1.9 2004/02/23 03:39:22 ian Exp $
 * @author Ian Darwin
 */
public class FileTree extends JPanel {

    /**
     * Construct a FileTree
     */
    public FileTree() {
    }

    public FileTree(String dir) {
        this(new File(dir));
    }

    public FileTree(File dir) {
    }

    /**
     * Add nodes from under "dir" into curTop. Highly recursive.
     */
    public DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        FileUtil u = new FileUtil(dir);
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(u);
        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector ol = new Vector();
        String[] tmp = dir.list();
        for (int i = 0; i < tmp.length; i++) {
            ol.addElement(tmp[i]);
        }
        Collections.sort(ol, String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector<String> files = new Vector<String>();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = (String) ol.elementAt(i);
            String newPath;
            if (curPath.equals(".")) {
                newPath = thisObject;
            } else {
                newPath = curPath + File.separator + thisObject;
            }
            if ((f = new File(newPath)).isDirectory()) {
                addNodes(curDir, f);
            } else {
                files.addElement(newPath);
            }
            //files.addElement(thisObject);
        }
        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++) {
            u = new FileUtil(new File(files.elementAt(fnum)));
            curDir.add(new DefaultMutableTreeNode(u));

        }
        return curDir;
    }
}

class FileUtil {

    public String name;
    public File path;

    public FileUtil(File f) {
        path = f;
        name = f.getName();
    }

    @Override
    public String toString() {
        return name;
    }
}