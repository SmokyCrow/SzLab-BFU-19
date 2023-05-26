import java.awt.*;

public class GPipe extends PassiveElement implements IViewable{
    /**
     * A cső konstruktora
     *
     * @param _id
     * @param _game
     */

    int x,y;
    IViewable end1, end2;
    public GPipe(int _id, Game _game) {
        super(_id, _game);
    }

    public void setEnd(IViewable e){
        setConnection((ActiveElement) e);
        if(end1 == null) {
            end1 = e;
        }
        else if(end2 == null){
            end2 = e;
        }
    }

    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawPipe(end1, end2, g);
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
