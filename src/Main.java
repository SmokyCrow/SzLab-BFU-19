import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {


        //A String list containing the name of all the use-cases
        List<String> cases = new ArrayList<>(Arrays.asList(
                "Mechanic controls pump",
                "Mechanic moves from pump to pipe",
                "Mechanic moves from pipe to pump",
                "Mechanic moves from pipe to cistern",
                "Mechanic moves from cistern to pipe",
                "Mechanic repair pipe",
                "Mechanic repair pump",
                "Mechanic pick up pipe from pump",
                "Mechanic pick up pipe from cistern",
                "Mechanic places pipe on pump",
                "Mechanic picks up pump",
                "Mechanic places pump",
                "Saboteur moves from pipe to cistern",
                "Saboteur moves from cistern to pipe",
                "Saboteur moves from pump to pipe",
                "Saboteur move from pipe to pump",
                "Saboteur damages pipe",
                "Saboteur controls pump"));

        /**
         *  Loop waiting for input to show use-cases
         */
        while (true) {
            for (int i = 0; i < cases.size(); i++) {
                System.out.println(i + ": " + cases.get(i));
            }

            System.out.println("Which case you want? (number from 0 - 17)");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String s = input.readLine();
            int p = Integer.parseInt(s);
            System.out.println("You've chosen to test " + cases.get(p));
            switch (s) {
                case "0":
                    m_control_pump();
                    break;
                case "1":
                    m_moves_pump_pipe();
                    break;
                case "2":
                    m_moves_pipe_pump();
                    break;
                case "3":
                    m_moves_pipe_cistern();
                    break;
                case "4":
                    m_moves_cistern_pipe();
                    break;
                case "5":
                    m_repair_pipe();
                    break;
                case "6":
                    m_repair_pump();
                    break;
                case "7":
                    m_pick_pipe_pump();
                    break;
                case "8":
                    m_pick_pipe_cistern();
                    break;
                case "9":
                    m_place_pipe_pump();
                    break;
                case "10":
                    m_pick_pump();
                    break;
                case "11":
                    m_place_pump();
                    break;
                case "12":
                    s_moves_pipe_cistern();
                    break;
                case "13":
                    s_moves_cistern_pipe();
                    break;
                case "14":
                    s_moves_pump_pipe();
                    break;
                case "15":
                    s_moves_pipe_pump();
                    break;
                case "16":
                    s_damages_pipe();
                    break;
                case "17":
                    s_control_pump();
                    break;
            }
            //Thread.sleep(2000);
            System.in.read();
        }
    }


    //Mechanic controls Pump
    private static void m_control_pump() {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new Pump();
        PassiveElement p1 = new PassiveElement();
        PassiveElement p2 = new PassiveElement();

        //Operations
        m.controlPump(p1, p2, depth);
        //Indentation
        System.out.print("\n");

    }

    //Mechanic moves from Pump to Pipe
    private static void m_moves_pump_pipe() throws IOException {
        //Initializing
        int depth = 0;
        int version = 1;
        Mechanic m = new Mechanic();
        m.element = new Pump();
        PassiveElement pipe = new PassiveElement();

        //Operations
        m.move(pipe, version);
        //Indentation
        System.out.print("\n");

    }

    //Mechanic moves from Pipe to Pump
    private static void m_moves_pipe_pump() throws IOException {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new PassiveElement();
        Pump pump = new Pump();

        //Operations
        m.move(pump, depth);
        //Indentation
        System.out.print("\n");

    }

    //Mechanic moves from Pipe to Cistern
    private static void m_moves_pipe_cistern() throws IOException {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new PassiveElement();
        Cistern cistern = new Cistern();

        //Operations
        m.move(cistern, depth);
        //Indentation
        System.out.print("\n");
    }

    //Mechanic moves from Cistern to Pipe
    private static void m_moves_cistern_pipe() throws IOException {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new PassiveElement();
        PassiveElement pipe = new PassiveElement();

        //Operations
        m.move(pipe, depth);
        //Indentation
        System.out.print("\n");

    }

    //Mechanic repairs Pipe
    private static void m_repair_pipe() {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new PassiveElement();
        m.element.setBroken(true);

        //Operations
        m.doElement(depth);
        //Indentation
        System.out.print("\n");
    }

    //Mechanic repairs Pump
    private static void m_repair_pump() {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new Pump();
        m.element.setBroken(true);

        //Operations
        m.doElement(depth);
        //Indentation
        System.out.print("\n");
    }

    //Mechanic picks Pipe from Pump
    private static void m_pick_pipe_pump() throws IOException {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new Pump();
        PassiveElement pipe = new PassiveElement();
        //Operations
        m.pickUpPipe(pipe, depth);
        //Indentation
        System.out.print("\n");
    }

    //Mechanic picks Pipe from Cistern
    private static void m_pick_pipe_cistern() throws IOException {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new Cistern();
        PassiveElement pipe = new PassiveElement();
        //Operations
        m.pickUpPipe(pipe, depth);
        //Indentation
        System.out.print("\n");
    }

    //Mechanic places pipe on pump
    private static void m_place_pipe_pump() throws IOException {
        //Initializing
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new Pump();
        PassiveElement pipe = new PassiveElement();
        //Operations
        m.placePipe(depth);
        //Indentation
        System.out.print("\n");
    }

    //Saboteur moves from Pump to Pipe
    private static void s_moves_pump_pipe() throws IOException {
        //Initializing
        int depth = 0;
        int version = 1;
        Saboteur s = new Saboteur();
        s.element = new Pump();
        PassiveElement pipe = new PassiveElement();

        //Operations
        s.move(pipe, version);
        //Indentation
        System.out.print("\n");
    }

    //Saboteur moves from Pipe to Pump
    private static void s_moves_pipe_pump() throws IOException {
        //Initializing
        int depth = 0;
        Saboteur s = new Saboteur();
        s.element = new PassiveElement();
        Pump pump = new Pump();

        //Operations
        s.move(pump, depth);
        //Indentation
        System.out.print("\n");
    }

    //Saboteur damages pipe
    private static void s_damages_pipe() {
        //Initializing
        int depth = 0;
        Saboteur s = new Saboteur();
        s.element = new PassiveElement();

        //Operations
        s.doElement(depth);
        //Indentation
        System.out.println("\n");
    }

    //Saboteur controls Pump
    private static void s_control_pump() {
        //Initializing
        int depth = 0;
        Saboteur s = new Saboteur();
        s.element = new Pump();
        PassiveElement p1 = new PassiveElement();
        PassiveElement p2 = new PassiveElement();

        //Operations
        s.controlPump(p1, p2, depth);
        //Indentation
        System.out.print("\n");
    }

    private static void m_pick_pump() {
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new Cistern();
        //Operations
        m.getPump(depth);
        //Indentation
        System.out.print("\n");
    }

    private static void m_place_pump() throws IOException {
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new PassiveElement();
        //Operations
        m.placePump(depth);
        //Indentation
        System.out.print("\n");
    }

    private static void s_moves_pipe_cistern() throws IOException {
        //Initializing
        int depth = 0;
        Saboteur s = new Saboteur();
        s.element = new PassiveElement();
        Cistern cistern = new Cistern();

        //Operations
        s.move(cistern, depth);
        //Indentation
        System.out.print("\n");
    }

    private static void s_moves_cistern_pipe() throws IOException {
        //Initializing
        int depth = 0;
        Saboteur s = new Saboteur();
        s.element = new PassiveElement();
        PassiveElement pipe = new PassiveElement();

        //Operations
        s.move(pipe, depth);
        //Indentation
        System.out.print("\n");

    }
}