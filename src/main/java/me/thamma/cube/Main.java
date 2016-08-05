package me.thamma.cube;

import me.thamma.cube.model.Cube;
import me.thamma.tools.render.render2d.PixMap;
import me.thamma.tools.render.render2d.Render2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String... args) throws Exception {
        //System.out.println(CubeUtils.perfectSolve(Cube.fromScramble("R U R' U' R' F R2 U' R' U' R U R' F' U2 R U R' U' R' F R2 U' R' U' R U R' F' U2")));
        //System.out.println(Algorithm.fromScramble("F U2 F2 D F2 U2 F R2 U F2 U F2 U2 R2 D' F2").translate(Turn.Y).mirror(Turn.Z));
        //PrintWriter out = null;
        //try {
        //    out = new PrintWriter("testfile.ppm");
        //} catch (FileNotFoundException e) {
        //    e.printStackTrace();
        //}
        Render2D render2D = new Render2D(Cube.fromScramble(""));
        PixMap pixMap = render2D.getPixmap().scale(5);
        //out.flush();
        //out.close();


        BufferedImage img = new BufferedImage(pixMap.getWidth(), pixMap.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < pixMap.getHeight(); i++)
            for (int j = 0; j < pixMap.getWidth(); j++)
                img.setRGB(j, i, pixMap.getPixel(i, j).getRGB());

        try {
            ImageIO.write(img, "png", new File("test.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
