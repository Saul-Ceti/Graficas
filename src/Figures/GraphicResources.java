package Figures;

import java.awt.*;

public class GraphicResources {
    // Importar la clase pixel
    Pixel pixel = new Pixel();

    public void Line(int x1, int y1, int x2, int y2, Color a) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;
        int e2;
        while (true) {
            pixel.PutPixel(x1, y1, a);
            if (x1 == x2 && y1 == y2) break;
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

    public void Circle(int xc, int yc, int r, Color a) {
        int x = r, y = 0;
        int p = 1 - r;
        while (x > y) {
            y++;
            if (p <= 0) p = p + 2 * y + 1;
            else {
                x--;
                p = p + 2 * y - 2 * x + 1;
            }
            if (x < y) break;
            pixel.PutPixel(xc + x, yc + y, a);
            pixel.PutPixel(xc - x, yc + y, a);
            pixel.PutPixel(xc + x, yc - y, a);
            pixel.PutPixel(xc - x, yc - y, a);
            if (x != y) {
                pixel.PutPixel(xc + y, yc + x, a);
                pixel.PutPixel(xc - y, yc + x, a);
                pixel.PutPixel(xc + y, yc - x, a);
                pixel.PutPixel(xc - y, yc - x, a);
            }
        }
    }

    public void Rectangle(int x1, int y1, int x2, int y2, Color a) {
        Line(x1, y1, x2, y1, a);
        Line(x2, y1, x2, y2, a);
        Line(x2, y2, x1, y2, a);
        Line(x1, y2, x1, y1, a);
    }

    public void Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color a) {
        Line(x1, y1, x2, y2, a);
        Line(x2, y2, x3, y3, a);
        Line(x3, y3, x1, y1, a);
    }

    public void Oval(int xc, int yc, int rx, int ry, Color a) {
        int x = 0, y = ry;
        int rx2 = rx * rx, ry2 = ry * ry;
        int p;
        int px = 0, py = 2 * rx2 * y;
        pixel.PutPixel(xc + x, yc + y, a);
        pixel.PutPixel(xc - x, yc + y, a);
        pixel.PutPixel(xc + x, yc - y, a);
        pixel.PutPixel(xc - x, yc - y, a);
        p = (int) Math.round(ry2 - (rx2 * ry) + (0.25 * rx2));
        while (px < py) {
            x++;
            px += 2 * ry2;
            if (p < 0) p += ry2 + px;
            else {
                y--;
                py -= 2 * rx2;
                p += ry2 + px - py;
            }
            pixel.PutPixel(xc + x, yc + y, a);
            pixel.PutPixel(xc - x, yc + y, a);
            pixel.PutPixel(xc + x, yc - y, a);
            pixel.PutPixel(xc - x, yc - y, a);
        }
        p = (int) Math.round(ry2 * (x + 0.5) * (x + 0.5) + rx2 * (y - 1) * (y - 1) - rx2 * ry2);
        while (y > 0) {
            y--;
            py -= 2 * rx2;
            if (p > 0) p += rx2 - py;
            else {
                x++;
                px += 2 * ry2;
                p += rx2 - py + px;
            }
            pixel.PutPixel(xc + x, yc + y, a);
            pixel.PutPixel(xc - x, yc + y, a);
            pixel.PutPixel(xc + x, yc - y, a);
            pixel.PutPixel(xc - x, yc - y, a);
        }
    }

    public void LineDDA(int x1, int y1, int x2, int y2, Color a) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        float pendiente = 0;

        pendiente = (float) dy / dx;

        if(pendiente <= 1 && pendiente >0){
            float x = x1;
            float y = y1;
            for (int i = 0; i < dx; i++) {
                pixel.PutPixel(Math.round(x), Math.round(y), a);
                x = x + 1;
                y = y + pendiente;
            }
        }
        else if(pendiente > 1){
            float x = x1;
            float y = y1;
            for (int i = 0; i < dy; i++) {
                pixel.PutPixel(Math.round(x), Math.round(y), a);
                x = x + 1/pendiente;
                y = y + 1;
            }
        }
        else if(pendiente <= -1){
            float x = x1;
            float y = y1;
            for (int i = 0; i < dy; i++) {
                pixel.PutPixel(Math.round(x), Math.round(y), a);
                x = x - 1/pendiente;
                y = y - 1;
            }
        }
    }
}
