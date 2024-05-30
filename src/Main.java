import Figures.GraphicResources;
import Figures.Pixel;
import Tools.Proyection;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Pixel pixel = new Pixel();
        GraphicResources resources = new GraphicResources(pixel);
        Proyection paralelProyection = new Proyection();

        int[][] coordenadasCuboParalelo = {
                // Cara frontal del cubo
                {0, 0, 0},
                {50, 0, 0},
                {50, 50, 0},
                {0, 50, 0},
                // Cara trasera del cubo
                {0, 0, -50},
                {50, 0, -50},
                {50, 50, -50},
                {0, 50, -50}
        };

        int[][] coordenadasT = {
                // Cara frontal de la T
                {50, 0, 0},
                {100,0, 0},
                {100, 50, 0},
                {150, 50, 0},
                {150, 100, 0},
                {100, 100, 0},
                {100, 150, 0},
                {50, 150, 0},
                // Cara trasera de la T
                {50, 0, -50},
                {100,0, -50},
                {100, 50, -50},
                {150, 50, -50},
                {150, 100, -50},
                {100, 100, -50},
                {100, 150, -50},
                {50, 150, -50}
        };

        resources.poligono3D(coordenadasT, Color.RED);
    }
}
