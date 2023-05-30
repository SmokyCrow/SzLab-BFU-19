import java.awt.*;
/**
 * A pumpa grafikus megjelenítéséért felelős osztály
 */
public class GPump extends Pump implements IViewable{
    int x, y;

    /**
     * Konstruktor
     * @param _id a pumpa azonosítója
     * @param _game a játék referenciája
     * @param _x koordináta
     * @param _y koordináta
     */
    public GPump(int _id, Game _game, int _x, int _y) {
        super(_id, _game);
        x = _x;
        y = _y;
    }

    /**
     * kirajzolja a pumpát
     * @param gamePanel játékpanel
     * @param g the <code>Graphics</code> context in which to paint
     */
    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawPump(this, x, y, g, broken);
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
     * beállítja az x koordinátát
     */
    public void setX(int _x){
        x = _x;
    }

    /**
     * visszaadja az y koordinátát
     * @return y
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * beállítja az y koordinátát
     */
    public void setY(int _y){
        y = _y;
    }
}
