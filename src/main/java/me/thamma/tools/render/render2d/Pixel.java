package me.thamma.tools.render.render2d;

public class Pixel {
    private int red, green, blue;

    public Pixel(int r, int g, int b) {
        this.red = r;
        this.green = g;
        this.blue = b;
    }

    public int getBlue() {
        return this.blue;
    }

    public int getGreen() {
        return this.green;
    }

    public int getRed() {
        return this.red;
    }

    public int getRGB() {
        return this.red << 16 | this.green << 8 | this.blue;
    }

    public String toString() {
        return String.format("%d %d %d ", this.red, this.green, this.blue);
    }

    public Pixel clone() {
        return new Pixel(this.red, this.blue, this.green);
    }
}
