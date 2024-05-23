package Tools;

import Figures.GraphicResources;
import Figures.Pixel;

import java.awt.*;

public class Proyection {
    public int[] paralelProyection(int x, int y, int z, int depth) {
        int x2 = (x - (x/depth)*x);
        int y2 = (y - (y/depth)*y);

        return new int[]{x2, y2};
    }
}
