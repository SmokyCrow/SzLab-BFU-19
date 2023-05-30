import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Main osztály
 */
public class Main {
    static Game game = new Game();

    /**

     A main metódus beolvassa a megadott fájlból az input parancsokat, lefuttatja a Game osztály megfelelő metódusait,
     és a kimenetet egy fájlba írja.
     A metódus egy string tömböt vesz át bemeneti argumentumként.
     @param args Egy string tömb, amely tartalmazza az input fájl nevét.
     @throws Exception ha hiba történik az input vagy output fájl olvasása vagy írása közben.
     */
    public static void main(String[] args) throws Exception {

        gameInit(game);
        GameFrame gameFrame = new GameFrame(game);
        gameFrame.update();

        while (true){
            if(gameFrame.getGamePanel().updateNeeded){
                gameFrame.update();
                gameFrame.getGamePanel().updateNeeded = false;
            }
        }
    }

    static void gameInit(Game game) throws Exception {
        game.addElement("so_1", 0, 200);
        game.addElement("pu_1", 400, 200);
        game.addElement("pi_1", 0, 0);
        game.addElement("ci_1", 805, 200);
        game.addElement("pi_2", 0, 0);

        game.connect("so_1", "pi_1");
        game.connect("pu_1", "pi_1");
        game.connect("pu_1", "pi_2");
        game.connect("ci_1", "pi_2");

        game.addPlayer("s_1", "so_1");
        game.addPlayer("s_2", "so_1");
        game.addPlayer("m_1", "pu_1");
        game.addPlayer("m_2", "ci_1");
        game.setPump("m_1", "pi_1", "pi_2");
        game.movePlayer("m_1", "pi_2");
        game.movePlayer("m_1", "ci_1");
        game.setMechanicPoints(0);
        game.setSaboteurPoints(0);
    }
}