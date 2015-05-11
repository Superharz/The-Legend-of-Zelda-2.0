/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Editor;

import java.io.File;

/**
 *
 * @author f.harz
 */
public class FileTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f=new File("Content/filename.txt");
        System.out.println(f.getAbsolutePath());
    }

}
