public class GCistern extends Cistern implements IViewable{
    /**
     * A ciszterna konstruktora
     *
     * @param _id   az elem azonosítója
     * @param _game a játék referenciája
     */

    int x,y;
    public GCistern(int _id, Game _game, int _x, int _y) {
        super(_id, _game);
        x = _x;
        y = _y;
    }

    @Override
    public void Draw(GamePanel gamePanel) {
        gamePanel.drawCistern(x, y);
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
