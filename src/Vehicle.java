import java.awt.Color;

public abstract class Vehicle extends Actor {
    protected int dir = 1;

    public Vehicle(Tile inLoc) {
        this.loc = inLoc;
        this.color = Color.RED;
    }

    public abstract void step(Grid grid);

    public boolean isOnTile(Grid grid, Tile tile) {
        return this.loc == tile;
    }
}