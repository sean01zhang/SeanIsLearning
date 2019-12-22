package seanMain;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.event.MouseAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ShadowPane extends JPanel {

    public ShadowPane() {
        setLayout(null);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // blur this.
        
        try {
			g.drawImage(getBlurredImage(ImageIO.read(new File("src/images/park-baseball.jpeg")),20), -10, -10, getWidth()+20,
					getHeight()+20, Color.BLACK, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    
    public BufferedImage createImage() {
        //finds the width and height of the panel so that the image can be saved 1:1
        int w = getWidth()+20;
        int h = getHeight()+20;
        //creates the bufferedImage.
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        //creates a graphics 2D instance. This allows for things to be drawn into the buffered image
        Graphics2D g = bi.createGraphics();
        //draws the panel to the buffered image.
        g.fillRect(-10, -10, getWidth()+20, getHeight()+20);
        //this.paint(g);
        g.dispose();
        return bi;
    }
    
    public static BufferedImage getBlurredImage(BufferedImage bi, int radius) {

        //this is for calculating the blur radius
        int size = radius * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];
        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }

        //creates an image kernel
        Kernel kernel = new Kernel(size, size, data);
        
        //blurs the image, leaving the edges of the blur non-blurred. This will be cropped out using
        //getSubImage.
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        //return op.filter(bi, null).getSubimage(radius, radius, bi.getWidth()-(2*radius), bi.getHeight()-(2*radius));
        return op.filter(bi, null);
    }
    
}