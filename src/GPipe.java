import java.awt.*;

public class GPipe extends PassiveElement implements IViewable{
    /**
     * A cső konstruktora
     *
     * @param _id
     * @param _game
     */


    public GPipe(int _id, Game _game) {
        super(_id, _game);
    }

    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawPipe(this, (IViewable) e1, (IViewable) e2, g, broken, getStickTime() > 0, getSlipTime() > 0);
    }

    @Override
    public int getX() {
        return Math.abs(((IViewable) e1).getX() + ((IViewable) e2).getX()) / 2;
    }

    @Override
    public int getY() {
        return Math.abs(((IViewable) e1).getY() + ((IViewable) e2).getY()) / 2;
    }
}
