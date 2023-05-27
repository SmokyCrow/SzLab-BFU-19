import java.awt.*;

public class GPump extends Pump implements IViewable{

    int x, y;
    public GPump(int _id, Game _game, int _x, int _y) {
        super(_id, _game);
        x = _x;
        y = _y;
    }

    @Override
    public void Draw(GamePanel gamePanel, Graphics g) {
        gamePanel.drawPump(x, y, g, broken);
    }

    @Override
    public int getX() {
        return x;
    }
    public void setX(int _x){
        x = _x;
    }

    @Override
    public int getY() {
        return y;
    }
    public void setY(int _y){
        y = _y;
    }
}
