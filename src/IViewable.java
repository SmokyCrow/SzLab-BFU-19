import java.awt.*;

public interface IViewable {
    public void Draw(GamePanel gamePanel, Graphics g);
    public int getX();
    public int getY();
}
