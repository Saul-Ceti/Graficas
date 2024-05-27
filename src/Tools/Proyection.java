package Tools;

public class Proyection {
    public int[] paralela(int x, int y, int z) {
        return new int[]{x, y};
    }

    public int[] oblicua(int x, int y, int z) {
        double theta = Math.toRadians(45);
        double k = 0.5;

        int xProyectado = (int) (x + k * z * Math.cos(theta));
        int yProyectado = (int) (y + k * z * Math.sin(theta));

        return new int[]{xProyectado, yProyectado};
    }

    public int[] ortogonal(int x, int y, int z) {
        return new int[]{x, y};
    }
}
