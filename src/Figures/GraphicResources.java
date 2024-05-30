package Figures;

import Tools.Fill;
import Tools.Proyection;

import java.awt.Color;

public class GraphicResources {
    private Pixel pixel;
    private Fill fill;
    final Proyection proyection;
    private int centerX = 400;
    private int centerY = 400;

    // Constructor
    public GraphicResources(Pixel pixel) {
        this.pixel = pixel;
        this.fill = new Fill(pixel);
        this.proyection = new Proyection();
    }

    // Linea usando el algoritmo de Bresenham
    public void linea(int x1, int y1, int x2, int y2, Color color) {
        int[] coordenadas = centroCartesiano(x1, x2, y1, y2);

        int dx = Math.abs(coordenadas[1] - coordenadas[0]);
        int dy = Math.abs(coordenadas[3] - coordenadas[2]);
        int sx = coordenadas[0] < coordenadas[1] ? 1 : -1;
        int sy = coordenadas[2] < coordenadas[3] ? 1 : -1;
        int err = dx - dy;
        int e2;

        while (true) {
            pixel.putPixel(coordenadas[0], coordenadas[2], color);

            if (coordenadas[0] == coordenadas[1] && coordenadas[2] == coordenadas[3]) {
                break;
            }

            e2 = 2 * err;

            if (e2 > -dy) {
                err = err - dy;
                coordenadas[0] = coordenadas[0] + sx;
            }

            if (e2 < dx) {
                err = err + dx;
                coordenadas[2] = coordenadas[2] + sy;
            }
        }
    }

    // Rectangulo
    public void rectangulo(int x1, int y1, int x2, int y2, Color color, boolean fill) {
        linea(x1, y1, x2, y1, color);
        linea(x2, y1, x2, y2, color);
        linea(x2, y2, x1, y2, color);
        linea(x1, y2, x1, y1, color);

        if (fill) {
            x1 += centerX;
            x2 += centerX;
            y1 = -y1;
            y2 = -y2;
            y1 += centerY;
            y2 += centerY;

            this.fill.floodFill((x1 + x2) / 2, (y1 + y2) / 2, pixel.getPixelColor((x1 + x2) / 2, (y1 + y2) / 2), color);
        }
    }

    // Poligono
    public void poligono(int[] x, int[] y, Color color, boolean fill) {
        for (int i = 0; i < x.length - 1; i++) {
            linea(x[i], y[i], x[i + 1], y[i + 1], color);
        }

        linea(x[x.length - 1], y[y.length - 1], x[0], y[0], color);

        if (fill) {
            int[] xPoints = new int[x.length];
            int[] yPoints = new int[y.length];

            for (int i = 0; i < x.length; i++) {
                xPoints[i] = x[i] + centerX;
                yPoints[i] = -y[i] + centerY;
            }

            this.fill.floodPolygon(xPoints, yPoints, pixel.getPixelColor(x[0] + centerX, -y[0] + centerY), color);
        }
    }

    public void poligono3D(int[][] coordenadas, Color color) {
        int aristas = coordenadas.length;
        int[][] conexionesVertices = new int[((aristas/2)*3)][2];

        // Conexión cara frontal
        for(int i = 0; i < aristas/2; i++){
            if(i == aristas/2 - 1){
                conexionesVertices[i][0] = i;
                conexionesVertices[i][1] = 0;
            } else {
                conexionesVertices[i][0] = i;
                conexionesVertices[i][1] = (i + 1);
            }
        }

        // Conexión cara trasera
        for(int i = aristas/2; i < aristas; i++){
            if(i == aristas - 1){
                conexionesVertices[i][0] = i;
                conexionesVertices[i][1] = aristas/2;
            } else {
                conexionesVertices[i][0] = i;
                conexionesVertices[i][1] = (i + 1);
            }
        }

        // Conexión entre caras frontal y trasera
        for(int i = aristas; i < (aristas/2)*3; i++){
            conexionesVertices[i][0] = i - aristas;
            conexionesVertices[i][1] = i - aristas + aristas/2;
        }

        dibujar3D(conexionesVertices, coordenadas, color);
    }

    private int[] centroCartesiano(int x1, int x2, int y1, int y2){
        x1 += centerX;
        x2 += centerX;
        y1 = -y1;
        y2 = -y2;
        y1 += centerY;
        y2 += centerY;

        return new int[]{x1, x2, y1, y2};
    }

    private void dibujar3D(int[][] conexionesVertices, int[][] coordenadas, Color color){
        int[] puntoDeVista = {10, 60, 20};

        for (int[] vertices : conexionesVertices) {
            int start = vertices[0];
            int end = vertices[1];

            int[] start2D = proyection.perspectiva(coordenadas[start][0], coordenadas[start][1], coordenadas[start][2], puntoDeVista);
            int[] end2D = proyection.perspectiva(coordenadas[end][0], coordenadas[end][1], coordenadas[end][2], puntoDeVista);

            int x1 = start2D[0];
            int y1 = start2D[1];
            int x2 = end2D[0];
            int y2 = end2D[1];

            linea(x1, y1, x2, y2, color);
        }
    }
}

