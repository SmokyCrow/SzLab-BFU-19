import java.awt.*;

public class GPipe extends PassiveElement implements IViewable{
    /**
     * A cső konstruktora
     *
     * @param _id
     * @param _game
     */

    int x,y;
    public GPipe(int _id, Game _game) {
        super(_id, _game);
    }

    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawPipe((IViewable) e1, (IViewable) e2, g);
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
