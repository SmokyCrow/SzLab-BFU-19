import java.awt.*;

public class GSource extends Source implements IViewable{
    /**
     * A Source osztály konstruktora.
     *
     * @param _id   az azonosító, amelyet a forrásnak beállítunk
     * @param _game a játék objektum, amelyben a forrás van
     */

    private int x,y;
    public GSource(int _id, Game _game, int _x, int _y) {
        super(_id, _game);
        x = _x;
        y = _y;
    }

    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawSource(this, x, y, g);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
