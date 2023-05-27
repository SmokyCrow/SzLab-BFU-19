import java.awt.*;

public class GMechanic extends Mechanic implements IViewable{
    /**
     * Konstruktor
     *
     * @param _id a mező azonosítója
     */
    public GMechanic(int _id) {
        super(_id);
    }

    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawMechanic(((IViewable) element).getX(), ((IViewable) element).getY(), g);
    }

    @Override
    public int getX() {
        return ((IViewable) element).getX();
    }

    @Override
    public int getY() {
        return ((IViewable) element).getY();
    }
}
