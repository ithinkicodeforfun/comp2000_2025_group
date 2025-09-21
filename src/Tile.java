import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Tile extends Rectangle {
    public static final int SIZE = 35;
    protected Color color;

    public Tile(int x, int y, Color color) {
        super(x, y, SIZE, SIZE);
        this.color = color;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, SIZE, SIZE);

        g.setColor(Color.BLACK);
        g.drawRect(x, y, SIZE, SIZE);
    }
}