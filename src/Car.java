import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Car<T> extends Vehicle {
    private final T type;
    private int length;

    //car class
    public Car(Tile inLoc, T type, int dir) {
        super(inLoc);
        this.type = type;
        this.dir = (dir >= 0) ? 1 : -1;

        if (type.equals("Bus")) {
            this.length = 4;
            this.color = Color.BLACK;
        } else if (type.equals("Truck")) {
            this.length = 3;
            this.color = Color.BLACK;
        } else if (type.equals("Car")) {
            this.length = 2;
            this.color = Color.BLACK;
        } else if (type.equals("Mini")) {
            this.length = 1;
            this.color = Color.BLACK;
        }
    }


    public T getType() { return type; }

    //car movement
    public void step(Grid grid) {
        int col = (loc.x - 10) / Tile.SIZE;
        int row = (loc.y - 10) / Tile.SIZE;

        col += dir;
        if (col < 0) col = 19;
        if (col > 19) col = 0;

        loc = grid.tileAtColRow(col, row);
    }

    //check collision
    public boolean isOnTile(Grid grid, Tile tile) {
        int headCol = (loc.x - 10) / Tile.SIZE;
        int row     = (loc.y - 10) / Tile.SIZE;

        for (int k = 0; k < length; k++) {
            int segCol = headCol - dir * k;
            if (segCol < 0) segCol += 20;
            if (segCol >= 20) segCol -= 20;
            if (grid.tileAtColRow(segCol, row) == tile) return true;
        }
        return false;
    }

    //draw method
    public void paint(Graphics g) {
        int headCol = (loc.x - 10) / Tile.SIZE;
        int row     = (loc.y - 10) / Tile.SIZE;

        for (int k = 0; k < length; k++) {
            int segCol = headCol - dir * k;
            if (segCol < 0) segCol += 20;
            if (segCol >= 20) segCol -= 20;

            int x = 10 + segCol * Tile.SIZE;
            int y = 10 + row     * Tile.SIZE;

            int inset = 5; 
            int size  = Tile.SIZE - inset * 2;

            g.setColor(color);
            g.fillRect(x + inset, y + inset, size, size);

        }
    }
}
