import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Stage {
    Grid grid;
    Player player;
    List<Coin> coins = new ArrayList<>();
    List<Vehicle> vehicles = new ArrayList<>();

    int score = 0;
    Random rng = new Random();

    private int ticks = 0;
    private static final int VEHICLE_SPEED_TICKS = 8;
    private int hitFlashTicks = 0;

    public Stage() {
        grid = new Grid();
        player = new Player(grid.tileAtColRow(0, 0));

        //coins
        for (int i = 0; i < 20; i++) coins.add(spawnCoin());

        //cars 
        spawnCar(1,  2, -1, "Car");
        spawnCar(1, 12, -1, "Car");
        spawnCar(2,  0, -1, "Car");
        spawnCar(2, 10, -1, "Car");
        spawnCar(2, 17, -1, "Mini");
        spawnCar(3,  0, -1, "Car");
        spawnCar(3,  5, -1, "Truck");
        spawnCar(3, 15, -1, "Truck");
        spawnCar(4,  2, -1, "Truck");
        spawnCar(4,  7, -1, "Car");
        spawnCar(4, 15, -1, "Car");
        spawnCar(6,  4, -1, "Car");
        spawnCar(6, 13, -1, "Car");
        spawnCar(7,  3, -1, "Car");
        spawnCar(7, 14, -1, "Car");
        spawnCar(7, 19, -1, "Car");
        spawnCar(8,  1, -1, "Car");
        spawnCar(8, 11, -1, "Truck");
        spawnCar(9,  6, -1, "Car");
        spawnCar(9, 16, -1, "Car");
        spawnCar(10, 3, -1, "Car");
        spawnCar(10,14, -1, "Car");
        spawnCar(11, 0, -1, "Truck");
        spawnCar(11, 9, -1, "Bus");
        spawnCar(12, 0, -1, "Bus");
        spawnCar(12,10, -1, "Car");
        spawnCar(13, 4, -1, "Car");
        spawnCar(13,17, -1, "Truck");
        spawnCar(15, 2, -1, "Car");
        spawnCar(15,13, -1, "Car");
        spawnCar(15,18, -1, "Mini");
        spawnCar(16, 0, -1, "Car");
        spawnCar(16, 8, -1, "Bus");
        spawnCar(16,18, -1, "Bus");
        spawnCar(17, 7, -1, "Truck");
        spawnCar(17,12, -1, "Car");
        spawnCar(18, 5, -1, "Car");
        spawnCar(18,15, -1, "Bus");
    }

    public void handleKey(int keyCode) {
        int col = (player.loc.x - 10) / Tile.SIZE;
        int row = (player.loc.y - 10) / Tile.SIZE;

        switch (keyCode) {
            case KeyEvent.VK_UP: row--; break;
            case KeyEvent.VK_DOWN: row++; break;
            case KeyEvent.VK_LEFT: col--; break;
            case KeyEvent.VK_RIGHT: col++; break;
        }

        if (col >= 0 && col < 20 && row >= 0 && row < 20) {
            player.loc = grid.tileAtColRow(col, row);
        }

        //coin pickup
        Iterator<Coin> it = coins.iterator();
        boolean collectedAny = false;
        while (it.hasNext()) {
            Coin coin = it.next();
            if (coin.loc == player.loc) {
                score++;
                it.remove();
                collectedAny = true;
            }
        }
        if (collectedAny) coins.add(spawnCoin());

        checkCol();
    }

    //spawns coins
    private Coin spawnCoin() {
        while (true) {
            int col = rng.nextInt(20);
            int row = rng.nextInt(20);
            Tile spot = grid.tileAtColRow(col, row);
            if (spot == player.loc) continue;
            boolean occupied = false;
            for (Coin c : coins) {
                if (c.loc == spot) { occupied = true; break; }
            }
            if (occupied) continue;
            return new Coin(spot);
        }
    }

    //spawns cars
    private void spawnCar(int row, int col, int dir, String type) {
        Tile tile = grid.tileAtColRow(col, row);
        vehicles.add(new Car<>(tile, type, dir));
    }
    

    private void updateGame() {
        ticks++;
        if (ticks % VEHICLE_SPEED_TICKS == 0) {
            for (Vehicle v : vehicles) v.step(grid);
            checkCol();
        }
        if (hitFlashTicks > 0) hitFlashTicks--;
    }

   //checks if vehicle collides with player
    private void checkCol() {
        for (Vehicle v : vehicles) {
            if (v.isOnTile(grid, player.loc)) {
                player.loc = grid.tileAtColRow(0, 0);
                score = 0;           
                hitFlashTicks = 20;  
                return;
            }
        }
    }
    
    //draw method 
    public void paint(Graphics g, Point mouseLoc) {
        updateGame();
        grid.paint(g, mouseLoc);

        for (Coin coin : coins) coin.paint(g);
        for (Vehicle v : vehicles) v.paint(g);
        player.paint(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 24));
        String text = "Score: " + score;
        int margin = 12, panelWidth = 720;
        int textWidth = g.getFontMetrics().stringWidth(text);
        int ascent = g.getFontMetrics().getAscent();
        int x = panelWidth - margin - textWidth, y = margin + ascent;
        g.drawString(text, x, y);

        if (hitFlashTicks > 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 36));
            String hit = "GAME OVER";
            int w = g.getFontMetrics().stringWidth(hit);
            g.drawString(hit, (panelWidth - w) / 2, 60);
        }
    }
}
