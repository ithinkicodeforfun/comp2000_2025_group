import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;

public class Stage {
    Grid grid;
    Actor cat;
    Actor dog;
    Actor bird;

    public Stage() {
        grid = new Grid();

        // start cat at row 0, col 0
        cat = new Player(grid.tileAtColRow(0, 0));
        dog = new Dog(grid.tileAtColRow(0, 15));
        bird = new Bird(grid.tileAtColRow(12, 9));
    }

    public void handleKey(int keyCode) {
        int col = cat.loc.x / Tile.SIZE;
        int row = cat.loc.y / Tile.SIZE;

        switch (keyCode) {
            case KeyEvent.VK_W: row--; break; 
            case KeyEvent.VK_S: row++; break;
            case KeyEvent.VK_A: col--; break; 
            case KeyEvent.VK_D: col++; break; 
        }

        // stay inside the 20x20 grid
        if (col >= 0 && col < 20 && row >= 0 && row < 20) {
            cat.loc = grid.tileAtColRow(col, row);
        }
    }

    public void paint(Graphics g, Point mouseLoc) {
        grid.paint(g, mouseLoc);
        cat.paint(g);
        dog.paint(g);
        bird.paint(g);
    }
}