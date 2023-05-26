import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public void update(Graphics g){
        drawBackGround(g);
        for (IViewable e: game.getGraphicList()) {
            e.Draw(this, g);
        }
    }

    public void drawBackGround(Graphics g){
        g.setColor(new Color(233, 168, 102));
        g.fillRect(0,0, size.width, size.height - 200);
        g.setColor(new Color(204, 102, 0));
        g.fillRect(0,size.height - 200, size.width, size.height - (size.height - 200));
    }

    public void drawMechanic(int x, int y, Graphics g){
        g.drawImage(images[1], x, y, null);
    }

    public void drawSaboteur(int x, int y, Graphics g){
        g.drawImage(images[0], x, y, null);
    }

    public void drawPump(int x, int y, Graphics g){
        g.drawImage(images[2], x, y, null);
    }

    public void drawCistern(int x, int y, Graphics g){
        g.drawImage(images[3], x, y, null);
    }

    public void drawSource(int x, int y, Graphics g){
        g.drawImage(images[4], x, y, null);
    }

    public void drawPipe(IViewable e1, IViewable e2, Graphics g){
        g.setColor(new Color(0,51,102));
        int thickness = 8;
        int offset = 16;

        int x1 = e1.getX()+offset;
        int y1 = e1.getY()+offset;
        int x2 = e2.getX()+offset;
        int y2 = e2.getY()+offset;

        int dX = x2 - x1;
        int dY = y2 - y1;
        // line length
        double lineLength = Math.sqrt(dX * dX + dY * dY);

        double scale = (double)(thickness) / (2 * lineLength);

        // The x,y increments from an endpoint needed to create a rectangle...
        double ddx = -scale * (double)dY;
        double ddy = scale * (double)dX;
        ddx += (ddx > 0) ? 0.5 : -0.5;
        ddy += (ddy > 0) ? 0.5 : -0.5;
        int dx = (int)ddx;
        int dy = (int)ddy;

        // Now we can compute the corner points...
        int xPoints[] = new int[4];
        int yPoints[] = new int[4];

        xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
        xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
        xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
        xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

        g.fillPolygon(xPoints, yPoints, 4);
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
        update(g);
    }

}