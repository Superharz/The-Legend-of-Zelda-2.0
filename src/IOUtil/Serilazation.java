/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IOUtil;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author f.harz
 */
public class Serilazation {
    


    public static boolean serialize(Object obj, String file) {
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream(file);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(obj);
         out.close();
         fileOut.close();
         
         System.out.printf("Serialized data is saved in "+file);
         return true;
      }catch(IOException i)
      {
          i.printStackTrace();
          return false; 
      }
    }
    public static Object deSerialize(String file) {
         try
        {
         FileInputStream fileIn = new FileInputStream(file);
         ObjectInputStream in = new ObjectInputStream(fileIn);
         Object obj =  in.readObject();
         in.close();
         fileIn.close();
         return obj;
      }catch(IOException i)
      {
         i.printStackTrace();
         return null;
      }catch(ClassNotFoundException c)
      {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return null;
      }
    }
     public static void main(String [] args)
    {
//        try {
//            BufferedImage img = ImageIO.read (new File("C:\\Users\\f.harz\\Desktop\\The-Legend-of-Zelda-2.0\\src\\Pictures\\tile1.png"));
//            JOptionPane.showMessageDialog(null, "Hi", "HO", 1, new ImageIcon(img));
//            byte[] byteArray = ((DataBufferByte) img.getData().getDataBuffer()).getData();
//            DataBufferByte b = new DataBufferByte(byteArray.length);
//            
//            img = ((BufferedImage) img.setData(img.getData().getDataBuffer().s)
//            serialize(img, "C:\\Users\\f.harz\\Desktop\\test.foo");
//        } catch (Exception e) {
//        }
        
        
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
