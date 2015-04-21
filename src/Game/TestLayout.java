/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Game;

import java.awt.Container;
import java.io.Serializable;
import javax.swing.GroupLayout;

/**
 *
 * @author f.harz
 */
public class TestLayout extends GroupLayout implements Serializable{
    public TestLayout (Container t) {
        super(t);
    }




    @Override
    public String toString() {
        return null;
    }
    public boolean equals(TestLayout object) {
        return false;
    }
    public void destroy() {

    }
    @Override
    public TestLayout clone() {
        return null;
    }
}
