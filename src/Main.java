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
        game.addElement("pu_1", 400, 100);
        game.addElement("pu_2", 400, 200);
        game.addElement("pu_3", 400, 300);
        game.addElement("pi_1", 0, 0);
        game.addElement("pi_2", 0, 0);
        game.addElement("pi_3", 0, 0);
        game.addElement("pi_4", 0, 0);
        game.addElement("pi_5", 0, 0);
        game.addElement("pi_6", 0, 0);
        game.addElement("pi_7", 0, 0);
        game.addElement("ci_1", 805, 300);
        game.addElement("ci_2", 805, 100);

        game.connect("so_1", "pi_1");
        game.connect("so_1", "pi_2");
        game.connect("so_1", "pi_3");
        game.connect("pu_1", "pi_1");
        game.connect("pu_1", "pi_7");
        game.connect("pu_2", "pi_2");
        game.connect("pu_2", "pi_4");
        game.connect("pu_2", "pi_5");
        game.connect("pu_2", "pi_7");
        game.connect("pu_3", "pi_3");
        game.connect("pu_3", "pi_6");
        game.connect("ci_1", "pi_4");
        game.connect("ci_1", "pi_6");
        game.connect("ci_2", "pi_5");


        game.addPlayer("s_1", "pu_1");
        game.addPlayer("s_2", "pu_3");
        game.addPlayer("m_1", "pu_2");
        game.addPlayer("m_2", "pi_5");

        game.setPump("s_1", "pi_1", "pi_7");
        game.setPump("s_2", "pi_3", "pi_6");
        game.setPump("m_1", "pi_2", "pi_4");

        game.movePlayer("s_1", "pi_1");
        game.movePlayer("s_2", "pi_3");
        game.movePlayer("m_1", "pi_4");

        game.setMechanicPoints(0);
        game.setSaboteurPoints(0);
    }
}