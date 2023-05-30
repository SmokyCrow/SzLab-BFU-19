import java.awt.*;

/**
 * A szerelő grafikus megjelenítéséért felelős osztály
 */
public class GMechanic extends Mechanic implements IViewable{
    /**
     * Konstruktor
     *
     * @param _id a mező azonosítója
     */
    public GMechanic(int _id) {
        super(_id);
    }

    /**
     * kirajzolja a szerelőt
     * @param gamePanel játékpanel
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawMechanic(((IViewable) element).getX(), ((IViewable) element).getY(), g);
    }

    /**
     * visszaadja az x koordinátá
     * @return x
     */
    @Override
    public int getX() {
        return ((IViewable) element).getX();
    }

    /**
     * visszaadja az y koordinátát
     * @return y
     */
    @Override
    public int getY() {
        return ((IViewable) element).getY();
    }
}
