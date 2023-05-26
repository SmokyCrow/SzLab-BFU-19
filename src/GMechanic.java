public class GMechanic extends Mechanic implements IViewable{

    int x, y;
    /**
     * Konstruktor
     *
     * @param _id a mező azonosítója
     */
    public GMechanic(int _id, int _x, int _y) {
        super(_id);
        x = _x;
        y = _y;
    }

    @Override
    public void Draw(GamePanel gamePanel) {
        gamePanel.drawMechanic(x, y);
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
