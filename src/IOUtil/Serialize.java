/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package IOUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author f.harz
 */
public class Serialize<T>{
    
    public void serialize(Object obj, String file) {
        try
      {
         FileOutputStream fileOut =
         new FileOutputStream(file);
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(obj);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in /tmp/employee.ser");
      }catch(IOException i)
      {
          i.printStackTrace();
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
