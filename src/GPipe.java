import java.awt.*;

/**
 * A ciszterna grafikus megjelenítéséért felelős osztály
 */
public class GPipe extends PassiveElement implements IViewable{
    /**
     * A cső konstruktora
     * @param _id azonosító
     * @param _game játék referencia
     */
    public GPipe(int _id, Game _game) {
        super(_id, _game);
    }

    /**
     * kirajzolja a csövet
     * @param gamePanel játékpanel
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawPipe(this, (IViewable) e1, (IViewable) e2, g, broken, getStickTime() > 0, getSlipTime() > 0);
    }

    /**
     * visszaadja az x koordinátá
     * @return x
     */
    @Override
    public int getX() {
        return Math.abs(((IViewable) e1).getX() + ((IViewable) e2).getX()) / 2;
    }

    /**
     * visszaadja az y koordinátát
     * @return y
     */
    @Override
    public int getY() {
        return Math.abs(((IViewable) e1).getY() + ((IViewable) e2).getY()) / 2;
    }
}
