import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
        this.images = new BufferedImage[8];
        images[0] = getImage("Saboteur.png");
        images[1] = getImage("Mechanic.png");
        images[2] = getImage("Pump.png");
        images[3] = getImage("Cistern.png");
        images[4] = getImage("Source.png");
        images[5] = getImage("Input.png");
        images[6] = getImage("Output.png");
        images[7] = getImage("Broken.png");
    }

    public void update(){
        GMechanic gm = new GMechanic(1, 32, 32);
        GSaboteur gs = new GSaboteur(1, 64, 64);
        gm.Draw(this);
        gs.Draw(this);
    }

    public void drawMechanic(int x, int y){
        Graphics g = super.getGraphics();
        g.drawImage(images[1], x, y, null);
    }

    public void drawSaboteur(int x, int y){
        Graphics g = super.getGraphics();
        g.drawImage(images[0], x, y, null);
    }

    private BufferedImage getImage(String name){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Images/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
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
