import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Stage {
    Grid grid;
    Player player;
    List<Coin> coins = new ArrayList<>();
    int score = 0;
    Random rng = new Random();

    public Stage() {
        grid = new Grid();
        player = new Player(grid.tileAtColRow(0, 0));

        //spawns 5 coins
        for (int i = 0; i < 5; i++) {
            coins.add(spawnRandomCoin());
        }
    }

    public void handleKey(int keyCode) {
        int col = player.loc.x / Tile.SIZE;
        int row = player.loc.y / Tile.SIZE;

        switch (keyCode) {
            case KeyEvent.VK_W: row--; break;
            case KeyEvent.VK_S: row++; break;
            case KeyEvent.VK_A: col--; break;
            case KeyEvent.VK_D: col++; break;
        }

        //grid border
        if (col >= 0 && col < 20 && row >= 0 && row < 20) {
            player.loc = grid.tileAtColRow(col, row);
        }

        //checks coin collision
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

        // respawn coins to always keep 5
        if (collectedAny) {
            coins.add(spawnRandomCoin());
        }
    }

    private Coin spawnRandomCoin() {
        while (true) {
            int col = rng.nextInt(20);
            int row = rng.nextInt(20);
            Tile spot = grid.tileAtColRow(col, row);

            //check player 
            if (spot == player.loc) continue;

            //check empty 
            boolean occupied = false;
            for (Coin c : coins) {
                if (c.loc == spot) { occupied = true; break; }
            }
            if (occupied) continue;

            return new Coin(spot);
        }
    }

    public void paint(Graphics g, Point mouseLoc) {
        grid.paint(g, mouseLoc);

        //coins
        for (Coin coin : coins) {
            coin.paint(g);
        }

        //player
        player.paint(g);

        //score
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 24));

        String text = "Score: " + score;
        int margin = 12;
        int panelWidth = 685; 
        int textWidth = g.getFontMetrics().stringWidth(text);
        int ascent = g.getFontMetrics().getAscent();

        int x = panelWidth - margin - textWidth;
        int y = margin + ascent;
        g.drawString(text, x, y);
    }
}
