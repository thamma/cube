package me.thamma.tools.render.render2d;

import me.thamma.cube.model.Cube;
import me.thamma.cube.model.Sticker;

import java.io.PrintWriter;

import static me.thamma.cube.model.Sticker.*;

public class Render2D {

    private Cube cube;

    private final int HEIGHT = 9, WIDTH = 12;
    Pixel black = new Pixel(0, 0, 0);
    Pixel blue = new Pixel(0, 0, 255);
    Pixel red = new Pixel(255, 0, 0);
    Pixel yellow = new Pixel(255,255, 0);
    Pixel green = new Pixel(0, 255, 0);
    Pixel orange = new Pixel(255, 165, 0);
    Pixel white = new Pixel(255, 255, 255);

    private final Pixel[] colorPixelMap = {black, blue, red, yellow, green, orange, white};
    //* U F R D B L null
    //* 1 2 3 4 5 6  0

    private final Sticker[] stickerTour = {
            null, null, null, ULB, UB, UBR, null, null, null, null, null, null,
            null, null, null, UL, U, UR, null, null, null, null, null, null,
            null, null, null, UFL, UF, URF, null, null, null, null, null, null,
            LBU, LU, LUF, FLU, FU, FUR, RFU, RU, RUB, BRU, BU, BUL,
            LB, L, LF, FL, F, FR, RF, R, RB, BR, B, BL,
            LDB, LD, LFD, FDL, FD, FRD, RDF, RD, RBD, BDR, BD, BLD,
            null, null, null, DLF, DF, DFR, null, null, null, null, null, null,
            null, null, null, DL, D, DR, null, null, null, null, null, null,
            null, null, null, DBL, DB, DRB, null, null, null, null, null, null,
    };

    public Render2D(Cube cube) {
        this.cube = cube;
    }

    public void generateImage(String path, int factor) {
        PixMap pixMap = loadGrid(this.cube);

        PrintWriter out = null;
        try {
            out = new PrintWriter(String.format("%s.ppm", path));
            out.print(pixMap.scale(factor).toString());
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PixMap getPixmap() {
        return loadGrid(this.cube);
    }

    public void generateImage(String path) {
        generateImage(path, 1);
    }

    private PixMap loadGrid(Cube cube) {
        PixMap out = new PixMap(HEIGHT, WIDTH);
        for (int i = 0; i < out.getHeight(); i++)
            for (int j = 0; j < out.getWidth(); j++)
                out.setPixel(getPixelFor(i, j), i, j);
        return out;
    }

    private Pixel getPixelFor(int i, int j) {
        return this.colorPixelMap[stickerTour[i * this.WIDTH + j] == null ? 0 : this.cube.getColor(stickerTour[i * this.WIDTH + j])].clone();
    }

}
