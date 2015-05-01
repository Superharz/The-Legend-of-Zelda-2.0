/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Editor;

import Game.Image;
import java.awt.Color;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author f.harz
 */
public class Content<T extends Image> extends JPanel{
    HashMap<JLabel, T> content;
    T selected;
    JLabel selectLabel;
    
    
    public Content() {
        //super();
        content = new HashMap<JLabel, T>();
        
    }
    
    public void add(T data) {
        JLabel l = new JLabel(data.getImageIcon());
                content.put(l,data);
                
                l.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mousePressed(java.awt.event.MouseEvent evt) {
                        ContentMousePressed(evt);
                    }
                    public void mouseEntered(java.awt.event.MouseEvent evt) {
                        //ItemMouseEntered(evt);
                        //p.show();
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        //ItemMouseExited(evt);
                        //p.show();
                    }
                });

                //table.put(l, item);
                l.setSize(l.getIcon().getIconWidth(),l.getIcon().getIconHeight()) ;

                this.add(l);
                update();
                System.out.println("Worked");
                l.setVisible(true);
    }
    private void update() {
        this.validate();
        this.repaint();
    }
    public void erase() {
        selectLabel.setBorder(null);
        selected = null;
    }
    private void ContentMousePressed(java.awt.event.MouseEvent evt) {
        JLabel l = (JLabel)evt.getComponent();
        T help = content.get(l);
        if (selected != null && selected != help) {
            
            selectLabel.setBorder(null);
        }
        selected = content.get(l);
        selectLabel = l;
        l.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.yellow));
        //System.out.println("HI");
        
    //table.get(l).setVisible(true);
}
    public T getContent() {
        return selected;
    }

    @Override
    public String toString() {
        return null;
    }
    public boolean equals(Content object) {
        return false;
    }
    public void destroy() {

    }
    @Override
    public Content clone() {
        return null;
    }
}
