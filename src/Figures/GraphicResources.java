package Figures;

import Tools.Fill;
import Tools.Proyection;

import java.awt.Color;

public class GraphicResources {
    private Pixel pixel;
    private Fill fill;
    private Proyection proyection;

    public GraphicResources(Pixel pixel) {
        this.pixel = pixel;
        this.fill = new Fill(pixel);
        this.proyection = new Proyection();
    }

    // Linea usando el algoritmo de Bresenham
    public void linea(int x1, int y1, int x2, int y2, Color color) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int e2;

        while (true) {
            pixel.putPixel(x1, y1, color);

            if (x1 == x2 && y1 == y2) {
                break;
            }

            e2 = 2 * err;

            if (e2 > -dy) {
                err = err - dy;
                x1 = x1 + sx;
            }

            if (e2 < dx) {
                err = err + dx;
                y1 = y1 + sy;
            }
        }
    }

    // Rectangulo
    public void rectangle(int x1, int y1, int x2, int y2, Color color, boolean fill) {
        linea(x1, y1, x2, y1, color);
        linea(x2, y1, x2, y2, color);
        linea(x2, y2, x1, y2, color);
        linea(x1, y2, x1, y1, color);

        if (fill) {
            this.fill.floodFill((x1 + x2) / 2, (y1 + y2) / 2, pixel.getPixelColor((x1 + x2) / 2, (y1 + y2) / 2), color);
        }
    }

    // Método para dibujar un cubo en proyección paralela en el centro de la pantalla
    public void drawCube(int centerX, int centerY, int size, Color color) {
        // Calculamos las coordenadas de los vértices del cubo
        int x1 = centerX - size / 2;
        int y1 = centerY - size / 2;
        int x2 = centerX + size / 2;
        int y2 = centerY + size / 2;

        // Dibujamos las caras del cubo
        rectangle(x1, y1, x2, y2, color, false);

        // Dibujamos las aristas del cubo
        linea(x1, y1, x1, y1 + size, color); // Arista trasera izquierda
        linea(x2, y1, x2, y1 + size, color); // Arista trasera derecha
        linea(x1, y1, x2, y1, color); // Arista superior
        linea(x1, y2, x2, y2, color); // Arista inferior
        linea(x1, y1 + size, x2, y2 + size, color); // Arista delantera izquierda
        linea(x2, y1 + size, x1, y2 + size, color); // Arista delantera derecha
    }
}

