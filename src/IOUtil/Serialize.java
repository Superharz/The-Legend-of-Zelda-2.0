package IOUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.thoughtworks.xstream.*;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.PrintWriter;

/**
 * This is the new Serialize-Class It uses a costume Serialization method from
 * an extern Library (XStream)
 *
 * @author Florian Harz
 */
public class Serialize {

    public static void xStreamOut(Object obj, String file) {
        xStreamOut(obj, new File(file));
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
}
