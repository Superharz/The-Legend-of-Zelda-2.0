package Tools;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

/**
 * This is a JPanel which shows a costume picture on it
 *
 * @author Florian Harz
 */
public class ImagePanel extends JPanel {

    private Image background;
    private Image animation;
    private BufferedImage img;
    private BufferedImage awesome;
    private int xdirection = 0, ydirection = 0;
    private int xBack = 0, yBack = 0;

    /**
     * Creates an ImagePanel and shows the given Image
     *
     * @param img The image wgich should be displayed
     */
    public ImagePanel(BufferedImage img) {
        super();
        this.img = img;
    }

    /**
     * Creates a new blank ImagePanel
     */
    public ImagePanel() {
        super();
    }

    /**
     * Sets the background Layer
     *
     * @param back The background picture
     */
    public void setBackground(Image back) {
        this.setBackground(back, 0, 0);
    }

    /**
     * Sets the background Layer at a specific point
     *
     * @param back The background picture
     * @param x The top left X-Coordinate
     * @param y The top left Y-Coordinate
     */
    public void setBackground(Image back, int x, int y) {
        try {
            xBack = x;
            yBack = y;
            background = back;
            repaint();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Sets the animation layer
     *
     * @param animation The animation picture
     * @param x The top left X-Coordinate
     * @param y The top left Y-Coordinate
     */
    public void setAnimation(Image animation, int x, int y) {
        try {
            this.xdirection = x;
            this.ydirection = y;
            this.animation = animation;
            repaint();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Returns the background Layer
     *
     * @return The background Picture
     */
    public BufferedImage getBackgroundImage() {
        return (BufferedImage) background;
    }

    /**
     * Sets the background layer
     *
     * @param file The file for the picture
     */
    public void setBackground(File file) {
        try {
            Image im = ImageIO.read(file);
            background = im;
            repaint();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     * Sets the foreground layer
     *
     * @param img The picture to set
     */
    public void setImage(BufferedImage img) {
        this.img = img;
        this.repaint();
    }

    /**
     * Sets the picture for a specific layer
     *
     * @param img The pictue to set
     * @param layer 0 = Background 1 = Foreground 2 = For-Foreground
     */
    public void setImage(BufferedImage img, int layer) {
        switch (layer) {
            case 0:
                background = img;
            case 1:
                this.img = img;
                break;
            case 2:
                awesome = img;
                break;
        }
        this.repaint();
    }

    /**
     * Paints the different layers for the ImagePanel
     *
     * @param g The Grphics of the element
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, xBack, yBack, this);
        g.drawImage(img, 0, 0, null);
        g.drawImage(animation, xdirection, ydirection, this);
        g.drawImage(awesome, 0, 0, null);
    }

    /**
     * Resets the background layer to null
     */
    void reSet() {
        background = null;
        repaint();
    }
}