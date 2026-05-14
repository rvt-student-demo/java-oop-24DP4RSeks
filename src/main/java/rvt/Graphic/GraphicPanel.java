package rvt.Graphic;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GraphicPanel extends JPanel {

    private BufferedImage myImage;

    public GraphicPanel() {
        loadImage();
    }

    // Constructor with layout
    public GraphicPanel(LayoutManager layout) {
        super(layout);
        loadImage();
    }

    private void loadImage() {
        try {
            myImage = ImageIO.read(new File("data/imagesBG/jdm.jpg"));
        } catch (IOException e) {
            System.out.println("Error: Could not find or read the image file.");
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (myImage != null) {
            g.drawImage(myImage, 0, 0, 1900, 100, this);
            g.drawImage(myImage, 0, 100, 1900, 100, this);
            g.drawImage(myImage, 0, 200, 1900, 100, this);
            g.drawImage(myImage, 0, 300, 1900, 100, this);
            g.drawImage(myImage, 0, 400, 1900, 100, this);
            g.drawImage(myImage, 0, 500, 1900, 100, this);
            g.drawImage(myImage, 0, 600, 1900, 100, this);
            g.drawImage(myImage, 0, 700, 1900, 100, this);
            g.drawImage(myImage, 0, 800, 1900, 100, this);
            g.drawImage(myImage, 0, 900, 1900, 100, this);
            g.drawImage(myImage, 0, 1000, 1900, 100, this);
            g.drawImage(myImage, 0, 1100, 1900, 100, this);

        } else {
            g.setColor(Color.RED);
            g.drawString("Image failed to load", 1900, 1000);
        }
    }
}
