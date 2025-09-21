import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame {
    public static void main(String[] args) {
        Main window = new Main();
        window.run();
    }

    class Canvas extends JPanel {
        Stage stage = new Stage();

        public Canvas() {
            setPreferredSize(new Dimension(720, 720));

            setFocusable(true);
            requestFocusInWindow();
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    stage.handleKey(e.getKeyCode()); 
                }
            });
        }

        @Override
        public void paint(Graphics g) {
            stage.paint(g, getMousePosition());
        }
    }

    private Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Canvas canvas = new Canvas();
        this.setContentPane(canvas);
        this.pack();
        this.setVisible(true);
    }

    public void run() {
        while (true) {
            repaint();
            try { Thread.sleep(50); } catch (Exception ignored) {}
        }
    }
}