public class GSaboteur extends Saboteur implements IViewable{
    /**
     * konstruktor
     *
     * @param _id a játékos azonosítója
     */
    int x,y;
    public GSaboteur(int _id, int _x, int _y) {
        super(_id);
        x = _x;
        y = _y;
    }

    @Override
    public void Draw(GamePanel gamePanel) {
        gamePanel.drawSaboteur(x,y);
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
