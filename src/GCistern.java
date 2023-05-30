import java.awt.*;

/**
 * A ciszterna grafikus megjelenítéséért felelős osztály
 */
public class GCistern extends Cistern implements IViewable{
    int x,y;

    /**
     * Konstruktor
     * @param _id a ciszterna azonosítója
     * @param _game a játék referenciája
     * @param _x koordináta
     * @param _y koordináta
     */
    public GCistern(int _id, Game _game, int _x, int _y) {
        super(_id, _game);
        x = _x;
        y = _y;
    }

    /**
     * kirajzolja a ciszternát
     * @param gamePanel játékpanel
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawCistern(this, x, y, g);
    }

    /**
     * visszaadja az x koordinátá
     * @return x
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     * visszaadja az y koordinátát
     * @return y
     */
    @Override
    public int getY() {
        return y;
    }
}
