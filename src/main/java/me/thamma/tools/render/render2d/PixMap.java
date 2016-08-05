package me.thamma.tools.render.render2d;

public class PixMap {

    private Pixel[][] pixels;

    private int height;


    private int width;

    public PixMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.pixels = new Pixel[height][width];
    }

    public PixMap scale(int factor) {
        PixMap out = new PixMap(this.height * factor, this.width * factor);
        for (int i = 0; i < this.height; i++)
            for (int j = 0; j < this.width; j++)
                for (int k = factor * i; k < factor * (i + 1); k++)
                    for (int l = factor * j; l < factor * (j + 1); l++)
                        out.setPixel(this.getPixel(i, j).clone(), k, l);
        return out;
    }

    public Pixel getPixel(int i, int j) {
        return this.pixels[i][j];
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++)
                stringBuilder.append(this.pixels[i][j]);
            stringBuilder.append("\n");
        }
        return String.format("P3\n%d %d\n%s", width, height, stringBuilder.toString());
    }

    public void setPixel(Pixel pixel, int row, int col) {
        this.pixels[row][col] = pixel.clone();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
