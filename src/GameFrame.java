import javax.swing.*;
import java.awt.*;

/**
 * A játék keretét adó osztály
 */
public class GameFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private GamePanel gamePanel;
    private Game game;

    /**
     * Konstruktor
     * @param _game a játék referenciája
     */
    public GameFrame(Game _game){
        init(_game);
        setResizable(false);
        setTitle("Sivatagi Csohalozat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(gamePanel);
        pack();
        setVisible(true);
    }

    /**
     * játék és panel inicializálás
     * @param _game a játék referenciája
     */
    public void init(Game _game){
        game = _game;
        gamePanel = new GamePanel(this.game);
    }

    /**
     * meghívja az update függvényt, ami frissíti a kérenyőt
     */
    public void update(){
        update(this.getGraphics());
    }

    /**
     * visszaadja a játékpanelt
     * @return a játékpanel
     */
    public GamePanel getGamePanel(){
        return gamePanel;
    }
}
