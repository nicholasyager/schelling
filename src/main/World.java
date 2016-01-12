package main;


import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class World {

    Random rand = new Random();

    private int width, height, numRaces;
    private double threshold;
    private int[][] world;

    private BufferedImage image;
    private List<Coord> unhappy;
    private Color[] colors;

    public World(int width, int height, int numRaces, double threshold) {
        world = new int[width][height];
        this.width = width;
        this.height = height;
        this.threshold = threshold;
        this.numRaces = numRaces;
        colors = generateColors(numRaces);

        image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_4BYTE_ABGR);

        generate();
    }

    private void generate() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                world[x][y] = rand.nextInt(numRaces);
                updateImage(x, y);
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void updateImage(int x, int y) {
        image.setRGB(x, y, colors[world[x][y]].getRGB());
    }

    private List<Coord> findUnhappy(List<Coord> previousUnhappy) {
        List<Coord> unhappy = new ArrayList<>();
        for (Coord previous : previousUnhappy) {
            if (isUnhappy(previous)) unhappy.add(previous);
        }
        return unhappy;
    }

    private List<Coord> findUnhappy() {
        List<Coord> unhappy = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Coord coord = new Coord(x, y, width, height);
                if (isUnhappy(coord)) unhappy.add(coord);
            }
        }
        return unhappy;
    }

    private boolean isUnhappy(Coord coord) {
        int race = world[coord.x][coord.y];
        double score = 0;
        for (Coord neighbor : coord.getNeighbors()) {
            if (world[neighbor.x][neighbor.y] != race) score++;
        }
        score /= 8;
        return score > threshold;
    }

    public void step() {
        if (unhappy == null) {
            unhappy = findUnhappy();
        } else {
            unhappy = findUnhappy(unhappy);
        }
        System.out.println(unhappy.size());
        List<Integer> races = getRaceList();
        Collections.shuffle(races);
        moveRaces(races);
    }

    private void moveRaces(List<Integer> races) {
        for (Coord coord : unhappy) {
            world[coord.x][coord.y] = races.remove(0);
            updateImage(coord.x, coord.y);
        }
    }

    @NotNull
    private List<Integer> getRaceList() {
        List<Integer> races = new ArrayList<>();
        for (Coord coord : unhappy) {
            races.add(world[coord.x][coord.y]);
        }
        return races;
    }

    public Color[] generateColors(int n) {
        Color[] cols = new Color[n];
        for (int i = 0; i < n; i++) {
            cols[i] = Color.getHSBColor((float) i / (float) n, 0.85f, 1.0f);
        }
        return cols;
    }
}
