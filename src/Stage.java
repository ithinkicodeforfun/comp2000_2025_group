import java.awt.Graphics;
import java.awt.Point;

public class Stage {
    Grid grid;
    Actor cat;
    Actor dog;
    Actor bird;

    public Stage() {
        grid = new Grid();

        cat = new Cat(grid.tileAtColRow(0, 0));
        dog = new Dog(grid.tileAtColRow(0, 15));
        bird = new Bird(grid.tileAtColRow(12, 9));
    }

    public void paint(Graphics g, Point mouseLoc) {
        grid.paint(g, mouseLoc);
        cat.paint(g);
        dog.paint(g);
        bird.paint(g);
    }
}
