import java.awt.Graphics;
import java.awt.Point;

public class Grid {
    Tile[][] tiles = new Tile[20][20];

    //background
    public Grid() {
        fillRow(0, Grass.class);
        fillRow(1, Road.class);
        fillRow(2, Road.class);
        fillRow(3, Road.class);
        fillRow(4, Road.class);
        fillRow(5, Grass.class);
        fillRow(6, Road.class);
        fillRow(7, Road.class);
        fillRow(8, Road.class);
        fillRow(9, Road.class);
        fillRow(10, Road.class);
        fillRow(11, Road.class);
        fillRow(12, Road.class);
        fillRow(13, Road.class);
        fillRow(14, Grass.class);
        fillRow(15, Road.class);
        fillRow(16, Road.class);
        fillRow(17, Road.class);
        fillRow(18, Road.class);
        fillRow(19, Grass.class);
    }

    //fillrow helper method
    private void fillRow(int row, Class<? extends Tile> tileType) {
        for (int col = 0; col < 20; col++) {
            int x = 10 + Tile.SIZE * col;
            int y = 10 + Tile.SIZE * row;

            if (tileType == Grass.class) {
                tiles[col][row] = new Grass(x, y);
            } else if (tileType == Road.class) {
                tiles[col][row] = new Road(x, y);
            }
        }
    }

    //paint method
    public void paint(Graphics g, Point mousePos) {
        for (int row = 0; row < 20; row++) {
            for (int col = 0; col < 20; col++) {
                if (tiles[col][row] != null) {
                    tiles[col][row].paint(g);
                }
            }
        }
    }

    //get tile location method
    public Tile tileAtColRow(int c, int r) {
        return tiles[c][r];
    }
}
