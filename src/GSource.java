import java.awt.*;
/**
 * A forrás grafikus megjelenítéséért felelős osztály
 */
public class GSource extends Source implements IViewable{
    private int x,y;
    /**
     * A GSource osztály konstruktora.
     *
     * @param _id   az azonosító, amelyet a forrásnak beállítunk
     * @param _game a játék objektum, amelyben a forrás van
     * @param _x koordináta
     * @param _y koordináta
     */
    public GSource(int _id, Game _game, int _x, int _y) {
        super(_id, _game);
        x = _x;
        y = _y;
    }

    /**
     * kirajzolja a forrást
     * @param gamePanel játékpanel
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawSource(this, x, y, g);
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
