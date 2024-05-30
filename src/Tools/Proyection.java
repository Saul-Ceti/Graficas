package Tools;

public class Proyection {
    public int[] plana(int x, int y, int z) {
        return new int[]{x, y};
    }

    public int[] oblicua(int x, int y, int z) {
        double theta = Math.toRadians(45);
        double k = 0.5;

        int xProyectado = (int) (x + k * z * Math.cos(theta));
        int yProyectado = (int) (y + k * z * Math.sin(theta));

        return new int[]{xProyectado, yProyectado};
    }

    public int[] perspectiva(int x, int y, int z, int[] puntoDeVista) {
        int xProyectado = puntoDeVista[0] + (x - puntoDeVista[0]) * (-(puntoDeVista[2])) / (z - puntoDeVista[2]);
        int yProyectado = puntoDeVista[1] + (y - puntoDeVista[1]) * (-(puntoDeVista[2])) / (z - puntoDeVista[2]);

        return new int[]{xProyectado, yProyectado};
    }
}
