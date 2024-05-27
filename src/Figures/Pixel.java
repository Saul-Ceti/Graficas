package Figures;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pixel extends JFrame {
    private BufferedImage buffer;
    private Graphics2D canvas;

    public Pixel() {
        setTitle("Recursos gráficos");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
        canvas = buffer.createGraphics();
        canvas.setColor(Color.WHITE);
        canvas.fillRect(0, 0, 800, 800);
        setVisible(true);

        //Dibujar un plano cartesiano en la ventana
        canvas.setColor(Color.BLACK);
        canvas.drawLine(400, 0, 400, 800);
        canvas.drawLine(0, 400, 800, 400);
    }

    public void putPixel(int x, int y, Color color) {
        buffer.setRGB(x, y, color.getRGB());
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(buffer, 0, 0, this);
    }

    public Graphics2D getCanvas() {
        return canvas;
    }

    public Color getPixelColor(int x, int y) {
        return new Color(buffer.getRGB(x, y));
    }
}
