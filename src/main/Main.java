package main;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException, IOException {

        int width = 500;
        int height = 500;

        System.out.println(width + " " + height);

        World world = new World(width, height, 3, 0.20);

        for (int i = 0; i < 1000; i++) {
            File f = new File("images/" + i + ".png");
            ImageIO.write(world.getImage(), "PNG", f);
            world.step();
        }

    }

}
