import java.awt.*;

public class GSaboteur extends Saboteur implements IViewable{
    /**
     * konstruktor
     *
     * @param _id a játékos azonosítója
     */
    public GSaboteur(int _id) {
        super(_id);
    }

    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawSaboteur(((IViewable) element).getX(), ((IViewable) element).getY(), g);
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }
}
