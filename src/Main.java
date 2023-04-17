import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A Main osztály
 */
public class Main {
    /**
     * Main függvény amely megvalósítja a különböző futtatható szcenáriókat
     * @param args
     * @throws IOException
     * @throws InterruptedException
     */
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
            /**
             * A felhasználó beírja az futtatni kívánt scenárió sorszámát,
             * a szabványos bemenetre, a program olvassa azt és elindítja az annak megfelelő forgatókönyvet
             */
            System.out.println("Which case you want? (number from 0 - 17)");
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            String s = input.readLine();
            int p = Integer.parseInt(s);
            while(p < 0 || p > 17){
                System.out.println("Wrong input! Try again:");
                s = input.readLine();
                p = Integer.parseInt(s);
            }
            System.out.println("You've chosen to test " + cases.get(p));
            /**
             * Switch amely a forgatókönyvek kiválasztását valósítja meg.
             * Adott értékre adott forgatókönyvet megvalósító függvény hívódik meg.
             */
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
            System.out.println("Press Enter to continue.");
            System.in.read();

        }
    }

    /**
     * A "Mechanic controls pump" forgatókönyvet megvalósító függvény.
     * A metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály controlPump metódusát,
     * amely szabályozza a Pump és a két PassiveElement közötti interakciót a megadott mélységen.
     * @throws NullPointerException Ha a Mechanic vagy a Pump osztály nem megfelelően lett inicializálva.
     */
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



    /**
     * A "Mechanic moves from Pump to Pipe" forghatókönyvet megvalósító metódus
     * Az m_moves_pump_pipe metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály move metódusát,
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Mechanic vagy a Pump osztály nem megfelelően lett inicializálva.
     */
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

    /**
     * A Mechanic moves from Pipe to Pump forgatókönyvet megvalósító függvény
     *Az m_moves_pipe_pump metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály move metódusát,
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Mechanic vagy a PassiveElement osztály nem megfelelően lett inicializálva.
     */
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

    /**
     * A Mechanic moves from Pipe to Cistern forgatókönyvet valósítja meg.
     * Az m_moves_pipe_cistern metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály move metódusát.
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Mechanic vagy a PassiveElement osztály nem megfelelően lett inicializálva.
     */
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



    /**
     * A Mechanic moves from Cistern to Pipe forgatókönyvet valósítja meg.
     * Azm_moves_cistern_pipe metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály move metódusát.
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Mechanic vagy a PassiveElement osztály nem megfelelően lett inicializálva.
     */
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
    /**
     * A Mechanic repairs Pipe forgatókönyvet valósítja meg.
     * Az m_repair_pipe metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály doElement metódusát.
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Mechanic vagy a PassiveElement osztály nem megfelelően lett inicializálva.
     */
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


    /**
     * A Mechanic repairs Pump forgatókönyvet valósítja meg.
     * Ez a függvény a Mechanic objektum által végrehajtott Pump elem javítási műveletet valósítja meg.
     * Először inicializál egy Mechanic objektumot, majd beállítja a javítandó Pump elemet és jelzi,
     * hogy az elem tönkrement.
     * Végül a Mechanic objektum elvégzi a javítási műveletet (doElement).
     * @throws IOException bármely input/output hiba esetén kivételt dob
     */
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

    /**
     * A Mechanic picks Pipe from Pump forgatókönyvet valósítja meg.
     * A Mechanic objektum felvehet egy passzív elemet a vízvezeték hálózatból.
     * Az metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály pickUpPipe metódusát.
     * @throws IOException
     */
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


    /**
     * A Mechanic picks Pipe from Cistern forgatókönyvet valósítja meg.
     * A szerelő által végrehajtott passzív elem (cső) felvételét megvalósító függvény a ciszternánál.
     * Inicializálja a szükséges változókat,
     * majd a megfelelő műveletet végrehajtva a mechanikus felvételzi a passzív elemet (csőt) (pickUpPipe).
     * @throws IOException ha bemeneti hiba történik a függvény végrehajtása közben
     */
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


    /**
     * A Mechanic places pipe on pump forgatókönyvet valósítja meg. Egy csövet köt be egy pumpába szerelő.
     * Inicializálja a szükséges változókat,
     * majd a megfelelő műveletet végrehajtva a mechanikus elhelyezi passzív elemet (csőt) (placePipe).
     * @throws IOException ha valami hiba történik a beolvasáskor
     */
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


    /**
     * A Saboteur moves from Pump to Pipe forgatókönyvet valósítja meg.
     * Inicializálja szükséges változókat, és a szabotőr move függvényével átlépteti a azt egy pumpáról egy csőre.
     * @throws IOException input/output hiba esetén
     */
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


    /**
     * A Saboteur moves from Pipe to Pump forgatókönyvet valósítja meg.
     * Inicializálja szükséges változókat, és a szabotőr move függvényével átlépteti a azt egy csőről egy pumpára.
     * @throws IOException input/output hiba esetén
     */
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

    /**
     * A Saboteur damages Pipe forgatókönyvet valósítja meg.
     * Az s_damages_pipe metódus inicializálja a szükséges változókat,
     * majd meghívja a Saboteur osztály doElement metódusát.
     * @throws NullPointerException Ha a Saboteur osztály vagy a PassiveElement objektum nem megfelelően lett inicializálva.
     */
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


    /**
     * A Saboteur controls Pump forgatókönyvet valósítja meg.
     * Az s_control_pump metódus inicializálja a szükséges változókat,
     * majd meghívja a Saboteur osztály controlPump metódusát.
     * @throws NullPointerException Ha a Saboteur osztály vagy a Pump objektum nem megfelelően lett inicializálva.
     */
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

    /**
     * A Mechanic picks up Pump forgatókönyvet valósítja meg.
     * Az m_pick_pump metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály getPump metódusát.
     * @throws NullPointerException Ha a Mechanic osztály vagy a Cistern objektum nem megfelelően lett inicializálva.
     */
    private static void m_pick_pump() {
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new Cistern();
        //Operations
        m.getPump(depth);
        //Indentation
        System.out.print("\n");
    }

    /**
     * A Mechanic places Pump forgatókönyvet valósítja meg.
     * Az m_place_pump metódus inicializálja a szükséges változókat,
     * majd meghívja a Mechanic osztály placePump metódusát.
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Mechanic osztály vagy a PassiveElement objektum nem megfelelően lett inicializálva.
     */
    private static void m_place_pump() throws IOException {
        int depth = 0;
        Mechanic m = new Mechanic();
        m.element = new PassiveElement();
        //Operations
        m.placePump(depth);
        //Indentation
        System.out.print("\n");
    }

    /**
     * A "Saboteur moves from pipe to cistern" forgatókönyvet valósítja meg.
     * Az s_moves_pipe_cistern metódus inicializálja a szükséges változókat,
     * majd meghívja a Saboteur osztály move metódusát.
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Saboteur vagy a Cistern osztály nem megfelelően lett inicializálva.
     */
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


    /**
     * A "Saboteur moves from cistern to pipe" forgatókönyvet valósítja meg a metódus.
     * Az s_moves_cistern_pipe metódus inicializálja a szükséges változókat,
     * majd meghívja a Saboteur osztály move metódusát.
     * @throws IOException Ha bármilyen I/O hiba történik.
     * @throws NullPointerException Ha a Saboteur vagy a PassiveElement osztály nem megfelelően lett inicializálva.
     */
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