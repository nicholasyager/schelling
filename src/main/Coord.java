package main;

import java.util.ArrayList;
import java.util.List;

public class Coord {

    public int x, y;
    public int width, height;

    private int[][] neighborMatrix = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}};

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coord(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        periodicBoundaryCheck();
    }

    private void periodicBoundaryCheck() {
        if (x < 0) {
            x += width;
        } else if (x >= width) {
            x -= width;
        }

        if (y < 0) {
            y += height;
        } else if (y >= height) {
            y -= height;
        }

    }

    public Coord offset(int offsetX, int offsetY) {
        return new Coord(x + offsetX, y + offsetY, width, height);
    }

    public List<Coord> getNeighbors() {
        List<Coord> neighbors = new ArrayList<>();

        for (int iter = 0; iter < 8; iter++) {
            neighbors.add(this.offset(neighborMatrix[iter][0], neighborMatrix[iter][1]));
        }

        return neighbors;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

}
