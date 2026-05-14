package rvt.Graphic;

import javax.swing.JFrame;

public class Graphic1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java grafika");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1980, 1080);
                
        GraphicPanel panel = new GraphicPanel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
