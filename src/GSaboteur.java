import java.awt.*;
/**
 * A szabotőr grafikus megjelenítéséért felelős osztály
 */
public class GSaboteur extends Saboteur implements IViewable{
    /**
     * konstruktor
     * @param _id a játékos azonosítója
     */
    public GSaboteur(int _id) {
        super(_id);
    }

    /**
     * kirajzolja a ciszternát
     * @param gamePanel játékpanel
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawSaboteur(((IViewable) element).getX(), ((IViewable) element).getY(), g);
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
