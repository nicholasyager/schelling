import main.Coord;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class CoordTest {

    private Random random = new Random();
    private int x = random.nextInt();
    private int y = random.nextInt();
    private int width = 100;
    private int height = 100;

    @Test
    public void test_coord_constructor() {
        Coord coord = new Coord(x, y);
        assertEquals(x, coord.x);
        assertEquals(y, coord.y);
    }

    @Test
    public void test_coord_constructor_width_bounded() {
        Coord coord = new Coord(x, y, width, height);
        assertEquals(width, coord.width);
        assertEquals(height, coord.height);
    }

    @Test
    public void test_coord_periodicBoundaryTest() {
        int x = 110;
        Coord coord = new Coord(x, y, width, height);
        assertEquals(x - width, coord.x);
    }

    @Test
    public void test_coord_neighbor_noBoundary() {
        Coord coord = new Coord(50, 50, 100, 100);
        List<Coord> neighbors = coord.getNeighbors();
        assertEquals(neighbors.size(),8);
    }

    @Test
    public void test_coord_neighbor_leftBoundary() {
        Coord coord = new Coord(0, 50, 100, 100);
        List<Coord> neighbors = coord.getNeighbors();
        for (Coord neighbor : neighbors) {
            System.out.println(neighbor);
        }
        assertEquals(neighbors.size(),8);
    }

    @Test
    public void test_coord_neighbor_topBoundary() {
        Coord coord = new Coord(50, 0, 100, 100);
        List<Coord> neighbors = coord.getNeighbors();
        for (Coord neighbor : neighbors) {
            System.out.println(neighbor);
        }
        assertEquals(neighbors.size(),8);
    }

}
