package Tools;
import Figures.Pixel;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Fill {
    private Pixel pixel;

    public Fill(Pixel pixel) {
        this.pixel = pixel;
    }

    public void floodFill(int x, int y, Color targetColor, Color replacementColor) {
        if (!targetColor.equals(replacementColor)) {
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(x, y));

            while (!queue.isEmpty()) {
                Point point = queue.poll();
                int px = (int) point.getX();
                int py = (int) point.getY();

                if (px >= 0 && px < pixel.getWidth() && py >= 0 && py < pixel.getHeight() &&
                        pixel.getPixelColor(px, py).equals(targetColor)) {
                    pixel.putPixel(px, py, replacementColor);
                    queue.add(new Point(px + 1, py));
                    queue.add(new Point(px - 1, py));
                    queue.add(new Point(px, py + 1));
                    queue.add(new Point(px, py - 1));
                }
            }
        }
    }

    public void floodPolygon(int[] x, int[] y, Color targetColor, Color replacementColor) {
        for (int i = 0; i < x.length; i++) {
            floodFill(x[i], y[i], targetColor, replacementColor);
        }
    }
}
