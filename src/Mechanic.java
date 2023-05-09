import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A szerelő szerepkört megvalósító osztály, ami a játékos által irányított karakterek egyike.
 */
public class Mechanic extends Player{

    /**
     * Attribútumok
     * -PassiveElement newPipe: az a cső mező, amelynek egyik végét a szerelő felvesz és
     * lerak egy másik pumpánál.
     * -Pump newPump: megmutatja, hogy van-e a szerelőnél pumpa: ha true- van, ha false - nincs
     */
    private Pump newPump;
    private PassiveElement newPipe;


    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public String toString() { return "mechanic";}

    /**
     * A szerelő megjavítja a pumpát(Pump), illetve a csövet (Pipe). A
     * függvény meghívja a jelenlegi mezőre (element) a repairElement() függvényt.
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void doElement(int depth) { //0
        System.out.println("->doElement()");
        depth +=1;
        element.repairElement(depth); //1
    }

    /**
     * A szerelő felvesz egy új pumpát (Pump) a ciszternánál. A függvény
     * a giveElement() függvényt hívja meg a mezőn (element), és felveszi a newPump-ba a kapott
     * pumpát (Pump), ha jó helyen áll.
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void getPump(int depth){ //0
        System.out.println("->getPump()");
        depth = depth + 1;
        element.giveElement(depth); //1
        Pump p = new Pump(); //2
        this.setNewPump(p, depth); //1
    }

    /**
     * A szerelő felvesz egy csövet (Pipe). A
     * függvény a giveElementEnd(p) függvényt hívja meg a mezőn (element), és a newPipe
     * attribútumába kapja meg a felvett csövet, ha jó helyen áll.
     * @param p a felvenni kívánt cső
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void pickUpPipe(PassiveElement p, int depth) throws IOException {
        System.out.println("->pickUpPipe(" + p.toString() + ")");
        depth += 1;
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->occupied()");
        System.out.println("\nIs the pipe occupied? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y")) {
                System.out.println("The pipe is already occupied.");
                return;
            }
            else if (s.equals("N")) {
                System.out.println("Does the mechanic have a pipe already? (Y/N)");
                while(true) {
                    s = reader.readLine();
                    if(s.equals("Y")) {
                        System.out.println("The mechanic already has a pipe.");
                        return;
                    }
                    else if (s.equals("N")) {
                        for (int i = 0; i < depth; i++) {
                            System.out.print("    ");
                        }
                        System.out.print("->giveElementEnd(" + p.toString() + ")\n");
                        boolean b = element.giveElementEnd(p); //1

                        if (b){
                            for (int i = 0; i < depth; i++) {
                                System.out.print("    ");
                                }
                                System.out.print("->setNewPipe(" + p.toString() + ")");
                                depth -= 1;
                                this.setNewPipe(p); //1
                            }
                        return;
                    }
                    else{
                        System.out.println("Wrong input, please try again!");
                    }
                }
            }
            else{
                System.out.println("Wrong input, please try again!");
            }
        }
    }

    /**
     * A szerelő beköti egy pumpába (Pump) a csövet (newPipe).A
     * függvény a jelenlegi mezőn (element) meghívja a placeElement(newPipe)-ot és ha az true-val
     * tér vissza, akkor törli a newPipe-ból a csövet (Pipe).
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void placePipe(int depth) throws IOException {
        System.out.println("->placePipe()");
        depth += 1;
        //this.newPipe = new PassiveElement();
        newPipe.setConnection((Pump) element);
        for(int i = 0; i < depth; i++){
            System.out.print("  ");
        }
        System.out.print("->isNeighbour(newPipe)\n");
        System.out.println("Was the connecting successful? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y")) {
                for(int i = 0; i < depth; i++){
                    System.out.print("  ");
                }
                System.out.print("->removeNewPipe()");
                this.removeNewPipe(); //1
                return;
            }
            else if (s.equals("N"))
                return;
            else{
                System.out.println("Wrong input, please try again!");
            }
        }
    }

    /**
     * Meghívja a jelenlegi mezőn (element), ha van a szerelőnél pumpa
     * (newPump != null) a placeElement()-et, ha az true-val tér vissza, az elhelyezés sikeres volt,
     * eldobja a newPump-ot magától.
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void placePump(int depth) throws IOException {
        System.out.println("->placePump()");
        this.newPump = new Pump();
        depth += 1;
        element.placeElement(newPump, depth);
        //felh. megkérdezni, hogy sikeres-e a lerakás
        System.out.println("Was the placing successful? (Y/N)");
        while(true) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String s = reader.readLine();
            if(s.equals("Y")) {
                this.removeNewPump(depth); //1
                return;
            }
            else if (s.equals("N"))
                return;
            else{
                System.out.println("Wrong input, please try again!");
            }
        }
    }

    /**
     * A függvény eltávolítja a szerelőnél lévő pumpát, amikor lerakja azt egy csőre
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void removeNewPump(int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.println("->removeNewPump()");

    }

    /**
     * Beállítja a paraméterként kapott pumpát a szerelő newPump attribútumába, amikor felveszi azt
     * @param p a felvett pumpa
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void setNewPump(Pump p, int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.println("->setNewPump()");
    }

    /**
     * A függvény eltávolítja a szerelőnél lévő csövet, amikor lerakja azt.
     */
    public void removeNewPipe(){

    }

    /**
     * Beállítja a paraméterként kapott csövet a szerelő newPipe attribútumába, amikor felveszi azt
     * @param p a felvett cső
     */
    public void setNewPipe(PassiveElement p){

    }

}
