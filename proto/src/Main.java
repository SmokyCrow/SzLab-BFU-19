import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A Main osztály
 */
public class Main {

    /**
     * Main függvény amely megvalósítja a különböző futtatható szcenáriókat
     */
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        RandomAccessFile rafInput = new RandomAccessFile("../" + args[0], "r");
        String testName = rafInput.readLine();
        List<String> allCommands = new ArrayList<>();
        String s;
        while ((s = rafInput.readLine()) != null) {
            allCommands.add(s);
        }
        rafInput.seek(0);
        rafInput.close();
        RandomAccessFile rafOutput = new RandomAccessFile("..\\ActualOutputs\\" + testName + ".txt", "rw");
        rafOutput.setLength(0);


        for (int i = 0; i < allCommands.size(); i++) {
            String command;
            String[] paramArray;
            String[] commands = allCommands.get(i).split(" ");
            command = commands[0];
            String params = commands[1];
            paramArray = params.split(",");

            switch (command) {
                case "initmap":
                    for (String value : paramArray) {
                        game.addElement(value);
                    }
                    break;

                case "connect":
                    game.connect(paramArray[0], paramArray[1]);
                    break;

                case "mechanic", "saboteur":
                    game.addPlayer(paramArray[0], paramArray[1]);
                    break;

                case "step":
                    game.movePlayer(paramArray[0], paramArray[1]);
                    break;

                case "repair":
                    game.repair(paramArray[0]);
                    break;

                case "damage":
                    game.damage(paramArray[0]);
                    break;

                case "set":
                    game.setPump(paramArray[0], paramArray[1], paramArray[2]);
                    break;

                case "pipeTamper":
                    game.pipeUpOrDown(paramArray[0], paramArray[1]);
                    break;

                case "pumpTamper":
                    game.pumpUpOrDown(paramArray[0]);
                    break;

                case "action":
                    game.makeAction(paramArray[0], paramArray[1]);
                    break;

                case "break":
                    game.randomBreak(paramArray[0]);
                    break;

                case "stat":
                    game.listMap(paramArray[0], rafOutput);
                    break;
            }
        }
        rafOutput.close();
    }
}