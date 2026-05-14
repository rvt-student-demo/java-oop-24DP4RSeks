package rvt.Graphic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
       g.setColor(Color.BLACK);
       g.setFont(Font.getFont("Arial").deriveFont(24f));
    }
}
