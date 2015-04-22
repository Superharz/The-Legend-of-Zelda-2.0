/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IOUtil;

import Inventory.Items;
import java.awt.image.BufferedImage;
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
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 *
 * @author f.harz
 */
public class Serialize{
    
    public void xStream(Object obj, String name) {
        XStream x = new XStream(new StaxDriver());
        
        
        
        
        
        
    }
    
    public static void serialize(Object obj, String file) {
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream(file);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(obj);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in " + file);
      }catch(IOException i)
      {
          i.printStackTrace();
      }
    }

    public <T> T deSerialize(Class<T> data, String file) {
        try
      { 
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
      }catch(IOException i)
      {
         i.printStackTrace();
         return null;
      }catch(ClassNotFoundException c)
      {
         System.out.println(data.getClass().getName() +" class not found");
         c.printStackTrace();
         return null;
      }
//        catch (InstantiationException ex) {
//            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        } catch (IllegalAccessException ex) {
//            Logger.getLogger(Serialize.class.getName()).log(Level.SEVERE, null, ex);
//            return null;
//        }
        
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
    
    public static void xmlEncode(Object obj, String file){
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
    
    public static void main(String [] args)
    {
        try {
            BufferedImage before = ImageIO.read (new File("C:\\Users\\f.harz\\Desktop\\The-Legend-of-Zelda-2.0\\src\\Pictures\\tile1.png"));
                ImageIcon icon = new ImageIcon(before);
                Items i = new Items("Wall","Wall", icon,true);
                i.addStats(new JLabel("Damage:  100     ")) ;
                i.addStats(new JLabel("Damage:  50      ")) ;
                i.addStats(new JLabel("Damage:  20      ")) ;
                i.addStats(new JLabel("Damage:  10      ")) ;
            //JLabel f = new JLabel("HI");
            serialize(i, "C:\\Users\\f.harz\\Desktop\\test.foo");
            Serialize s = new Serialize();
            i = s.deSerialize(i.getClass(), "C:\\Users\\f.harz\\Desktop\\test.foo");
            for (int j = 0; j < i.getLength(); j++) {
                System.out.println(i.getStat(j).getText());
            }
            
            //xmlEncode(i, "C:\\Users\\f.harz\\Desktop\\testr.xml");
//            Serialize<JLabel> s = new Serialize<JLabel>();
//            f=s.deSerialize(f, "C:\\Users\\f.harz\\Desktop\\test.foo");
//            System.out.println(f.getText());
//            f=s.xmlDecode(f, "C:\\Users\\f.harz\\Desktop\\test.xml");
//            System.out.println(f.getText());
    //        try {
    //            BufferedImage img = ImageIO.read (new File("C:\\Users\\f.harz\\Desktop\\The-Legend-of-Zelda-2.0\\src\\Pictures\\tile1.png"));
    //            JOptionPane.showMessageDialog(null, "Hi", "HO", 1, new ImageIcon(img));
    //            byte[] byteArray = ((DataBufferByte) img.getData().getDataBuffer()).getData();
    //            DataBufferByte b = new DataBufferByte(byteArray.length);
    //            
    //            img = ((BufferedImage) img.setData(img.getData().getDataBuffer().s)
    //            serialize(img, "C:\\Users\\f.harz\\Desktop\\test.foo");
    //        }
    //        }
        } catch (IOException ex) {
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
