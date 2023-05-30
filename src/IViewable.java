import java.awt.*;

/**
 * Interface a grafikus obeójektumok összefogásáre
 */
public interface IViewable {
    /**
     * megrajzolja az objektumot, feülfdefiniálva
     * @param gamePanel játékpanel
     * @param g
     */
    public void Draw(GamePanel gamePanel, Graphics g);

    /**
     * visszaadja az x koordinátá
     * @return x
     */
    public int getX();

    /**
     * visszaadja az y koordinátát
     * @return y
     */
    public int getY();
}
