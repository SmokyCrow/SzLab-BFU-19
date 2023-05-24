import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class GamePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private Game game;
    private Dimension size;
    private BufferedImage[] images;

    public GamePanel(Game game) {
        this.game = game;
        initImages();
        setPanelSize();
    }

    private void initImages(){
        BufferedImage temp = null;
        InputStream is = GamePanel.class.getClassLoader().getResourceAsStream("Saboteur.png");
    }

    private void setPanelSize() {
        size = new Dimension(640, 600);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
