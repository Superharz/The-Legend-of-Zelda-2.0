/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IOUtil;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.PrintWriter;

/**
 *
 * @author f.harz
 */
public class Serialize {

    public static void xStreamOut(Object obj, String file) {
        PrintWriter out = null;
        XStream x = new XStream(new StaxDriver());
        try {
            String xml = x.toXML(obj);
            out = new PrintWriter(file);
            out.write(xml);
            out.close();
            System.out.println("Serialized data is saved in " + file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    public static void xStreamOut(Object obj, File file) {
        PrintWriter out = null;
        XStream x = new XStream(new StaxDriver());
        try {
            String xml = x.toXML(obj);
            out = new PrintWriter(file);
            out.write(xml);
            out.close();
            System.out.println("Serialized data is saved in " + file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    public static <T> T xStreamIn(Class<T> data, String file) {
        return xStreamIn(data, new File(file));
    }

    public static <T> T xStreamIn(Class<T> data, File file) {
        T clazz;
        XStream x = new XStream(new StaxDriver());
        clazz = data.cast(x.fromXML(file));
        return clazz;
    }

    public static void serialize(Object obj, String file) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(obj);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + file);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static <T> T deSerialize(Class<T> data, String file) {
        try {
            T clazz;
            //T clazz = data.newInstance();
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            clazz = data.cast(in.readObject());
            //data = (data.cast(in.readObject())) ;
            in.close();
            fileIn.close();
            return clazz;
            //return data.newInstance();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println(data.getClass().getName() + " class not found");
            c.printStackTrace();
            return null;
        }
    }

    public <T> T xmlDecode(Class<T> data, String file) {
        try {
            T clazz;
            XMLDecoder d = new XMLDecoder(
                    new BufferedInputStream(
                    new FileInputStream(file)));
            clazz = data.cast(d.readObject());
            //data = (T)d.readObject();
            d.close();
            return clazz;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static void xmlEncode(Object obj, String file) {
        try {
            XMLEncoder e = new XMLEncoder(
                    new BufferedOutputStream(
                    new FileOutputStream(file)));
            e.writeObject(obj);
            e.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return null;
    }

    public boolean equals(Serialize object) {
        return false;
    }

    public void destroy() {
    }

    @Override
    public Serialize clone() {
        return null;
    }
}
