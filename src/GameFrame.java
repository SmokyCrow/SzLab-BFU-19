import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private GamePanel gamePanel;
    private Game game;

    public GameFrame(Game _game){
        init(_game);
        setResizable(false);
        setTitle("Sivatagi Csohalozat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gamePanel);
        pack();

        setVisible(true);
    }

    public void init(Game _game){
        game = _game;
        gamePanel = new GamePanel(this.game, this.getGraphics());
    }

    public void update(){
        update(this.getGraphics());
    }
}
