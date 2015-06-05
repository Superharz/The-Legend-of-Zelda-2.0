/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IOUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author f.harz
 */
public class Serilazation {

    public static boolean serialize(Object obj, String file) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();

            System.out.printf("Serialized data is saved in " + file);
            return true;
        } catch (IOException i) {
            i.printStackTrace();
            return false;
        }
    }

    public static Object deSerialize(String file) {
        try {
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Object obj = in.readObject();
            in.close();
            fileIn.close();
            return obj;
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return null;
    }

    public boolean equals(Serilazation object) {
        return false;
    }

    public void destroy() {
    }

    @Override
    public Serilazation clone() {
        return null;
    }
}
