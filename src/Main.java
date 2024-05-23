import Figures.GraphicResources;
import Figures.Pixel;
import Tools.Proyection;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Pixel pixel = new Pixel();
        GraphicResources resources = new GraphicResources(pixel);
        Proyection paralelProyection = new Proyection();

        resources.drawCube(100, 100, 50, Color.RED);
    }
}
