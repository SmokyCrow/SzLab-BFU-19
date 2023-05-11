import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A Main osztály
 */
public class Main {

    /**
     * Main függvény amely megvalósítja a különböző futtatható szcenáriókat
     */
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        while (true) {


            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String s = input.readLine();
            String command;
            String[] paramArray;
            String[] commands = s.split(" ");
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

                case "PipeTamper":
                    game.pickUpPipe(paramArray[0], paramArray[1]);
                    break;

                case "PumpTamper":
                    game.pumpUpOrDown(paramArray[0]);
                    break;
            }

            game.listMap();

        }
    }

    //    private static void m_control_pump() {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new Pump();
//        PassiveElement p1 = new PassiveElement();
//        PassiveElement p2 = new PassiveElement();
//
//        //Operations
//        m.controlPump(p1, p2, depth);
//        //Indentation
//        System.out.print("\n");
//
//    }


    //    private static void m_moves_pump_pipe() throws IOException {
//        //Initializing
//        int depth = 0;
//        int version = 1;
//        Mechanic m = new Mechanic();
//        m.element = new Pump();
//        PassiveElement pipe = new PassiveElement();
//
//        //Operations
//        m.move(pipe, version);
//        //Indentation
//        System.out.print("\n");
//
//    }

    //    private static void m_moves_pipe_pump() throws IOException {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new PassiveElement();
//        Pump pump = new Pump();
//
//        //Operations
//        m.move(pump, depth);
//        //Indentation
//        System.out.print("\n");
//
//    }

    //    private static void m_moves_pipe_cistern() throws IOException {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new PassiveElement();
//        Cistern cistern = new Cistern();
//
//        //Operations
//        m.move(cistern, depth);
//        //Indentation
//        System.out.print("\n");
//    }


    //    private static void m_moves_cistern_pipe() throws IOException {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new PassiveElement();
//        PassiveElement pipe = new PassiveElement();
//
//        //Operations
//        m.move(pipe, depth);
//        //Indentation
//        System.out.print("\n");
//
//    }
    //    private static void m_repair_pipe() {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new PassiveElement();
//        m.element.setBroken(true);
//
//        //Operations
//        m.doElement(depth);
//        //Indentation
//        System.out.print("\n");
//    }


    //    private static void m_repair_pump() {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new Pump();
//        m.element.setBroken(true);
//
//        //Operations
//        m.doElement(depth);
//        //Indentation
//        System.out.print("\n");
//    }

    //    private static void m_pick_pipe_pump() throws IOException {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new Pump();
//        PassiveElement pipe = new PassiveElement();
//        //Operations
//        m.pickUpPipe(pipe, depth);
//        //Indentation
//        System.out.print("\n");
//    }


    //    private static void m_pick_pipe_cistern() throws IOException {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new Cistern();
//        PassiveElement pipe = new PassiveElement();
//        //Operations
//        m.pickUpPipe(pipe, depth);
//        //Indentation
//        System.out.print("\n");
//    }


    //    private static void m_place_pipe_pump() throws IOException {
//        //Initializing
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new Pump();
//        PassiveElement pipe = new PassiveElement();
//        //Operations
//        m.placePipe(depth);
//        //Indentation
//        System.out.print("\n");
//    }


    //    private static void s_moves_pump_pipe() throws IOException {
//        //Initializing
//        int depth = 0;
//        int version = 1;
//        Saboteur s = new Saboteur();
//        s.element = new Pump();
//        PassiveElement pipe = new PassiveElement();
//
//        //Operations
//        s.move(pipe, version);
//        //Indentation
//        System.out.print("\n");
//    }


    //    private static void s_moves_pipe_pump() throws IOException {
//        //Initializing
//        int depth = 0;
//        Saboteur s = new Saboteur();
//        s.element = new PassiveElement();
//        Pump pump = new Pump();
//
//        //Operations
//        s.move(pump, depth);
//        //Indentation
//        System.out.print("\n");
//    }

    //    private static void s_damages_pipe() {
//            //Initializing
//            int depth = 0;
//            Saboteur s = new Saboteur();
//            s.element = new PassiveElement();
//
//            //Operations
//            s.doElement(depth);
//            //Indentation
//            System.out.println("\n");
//    }


    //    private static void s_control_pump() {
//        //Initializing
//        int depth = 0;
//        Saboteur s = new Saboteur();
//        s.element = new Pump();
//        PassiveElement p1 = new PassiveElement();
//        PassiveElement p2 = new PassiveElement();
//
//        //Operations
//        s.controlPump(p1, p2, depth);
//        //Indentation
//        System.out.print("\n");
//    }

    //    private static void m_pick_pump() {
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new Cistern();
//        //Operations
//        m.getPump(depth);
//        //Indentation
//        System.out.print("\n");
//    }

    //    private static void m_place_pump() throws IOException {
//        int depth = 0;
//        Mechanic m = new Mechanic();
//        m.element = new PassiveElement();
//        //Operations
//        m.placePump(depth);
//        //Indentation
//        System.out.print("\n");
//    }

    //    private static void s_moves_pipe_cistern() throws IOException {
//        //Initializing
//        int depth = 0;
//        Saboteur s = new Saboteur();
//        s.element = new PassiveElement();
//        Cistern cistern = new Cistern();
//
//        //Operations
//        s.move(cistern, depth);
//        //Indentation
//        System.out.print("\n");
//    }


    //    private static void s_moves_cistern_pipe() throws IOException {
//        //Initializing
//        int depth = 0;
//        Saboteur s = new Saboteur();
//        s.element = new PassiveElement();
//        PassiveElement pipe = new PassiveElement();
//
//        //Operations
//        s.move(pipe, depth);
//        //Indentation
//        System.out.print("\n");
//
//    }
}