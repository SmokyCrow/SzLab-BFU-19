import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Main osztály
 */
public class Main {

    /**

     A main metódus beolvassa a megadott fájlból az input parancsokat, lefuttatja a Game osztály megfelelő metódusait,
     és a kimenetet egy fájlba írja.
     A metódus egy string tömböt vesz át bemeneti argumentumként.
     @param args Egy string tömb, amely tartalmazza az input fájl nevét.
     @throws Exception ha hiba történik az input vagy output fájl olvasása vagy írása közben.
     */
    public static void main(String[] args) throws Exception {
        Game game = new Game();

        game.addElement("so_1", 32, 32);
        game.addElement("pu_1", 128, 128);
        game.addElement("pi_1", 0, 0);
        game.connect("so_1", "pi_1");
        game.connect("pu_1", "pi_1");

        game.addElement("ci_1", 256, 128);
        game.addElement("pi_2", 0, 0);
        game.connect("pu_1", "pi_2");
        game.connect("ci_1", "pi_2");
        game.addPlayer("s_1", "pi_1");
        game.addPlayer("m_1", "pu_1");
        //game.makeAction("s_1", "slip");
        //game.movePlayer("s_1", "pu_1");
        game.randomBreak("pu_1");

        GameFrame gameFrame = new GameFrame(game);
        gameFrame.update();
        while (true){
            if(gameFrame.getGamePanel().updateNeeded){
                gameFrame.update();
                gameFrame.getGamePanel().updateNeeded = false;
            }
        }
//        RandomAccessFile rafInput = new RandomAccessFile("../" + args[0], "r");
//        String testName = rafInput.readLine();
//        List<String> allCommands = new ArrayList<>();
//        String s;
//        while ((s = rafInput.readLine()) != null) {
//            allCommands.add(s);
//        }
//        rafInput.seek(0);
//        rafInput.close();
//        RandomAccessFile rafOutput = new RandomAccessFile("..\\ActualOutputs\\" + testName + ".txt", "rw");
//        rafOutput.setLength(0);
//
//
//        for (int i = 0; i < allCommands.size(); i++) {
//            String command;
//            String[] paramArray;
//            String[] commands = allCommands.get(i).split(" ");
//            command = commands[0];
//            String params = commands[1];
//            paramArray = params.split(",");
//
//            switch (command) {
//                case "initmap":
//                    for (String value : paramArray) {
//                        game.addElement(value);
//                    }
//                    break;
//
//                case "connect":
//                    game.connect(paramArray[0], paramArray[1]);
//                    break;
//
//                case "mechanic", "saboteur":
//                    game.addPlayer(paramArray[0], paramArray[1]);
//                    break;
//
//                case "step":
//                    game.movePlayer(paramArray[0], paramArray[1]);
//                    break;
//
//                case "repair":
//                    game.repair(paramArray[0]);
//                    break;
//
//                case "damage":
//                    game.damage(paramArray[0]);
//                    break;
//
//                case "set":
//                    game.setPump(paramArray[0], paramArray[1], paramArray[2]);
//                    break;
//
//                case "pipeTamper":
//                    game.pipeUpOrDown(paramArray[0], paramArray[1]);
//                    break;
//
//                case "pumpTamper":
//                    game.pumpUpOrDown(paramArray[0]);
//                    break;
//
//                case "action":
//                    game.makeAction(paramArray[0], paramArray[1]);
//                    break;
//
//                case "break":
//                    game.randomBreak(paramArray[0]);
//                    break;
//
//                case "stat":
//                    game.listMap(paramArray[0], rafOutput);
//                    break;
//            }
//        }
//        rafOutput.close();
    }
}