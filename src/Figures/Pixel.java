package Figures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pixel extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public Pixel() {
        setTitle("Figures.Pixel");
        setSize(500, 500);
        setLayout(null);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.getGraphics();
        setVisible(true);
    }

    // Pintar un pixel en la pantalla
    public void PutPixel(int x, int y, Color a){
        buffer.setRGB(0, 0, a.getRGB());
        getGraphics().drawImage(buffer, x, y, this);
    }
}
