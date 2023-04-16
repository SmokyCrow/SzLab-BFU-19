import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A játékos kasztokat leíró absztrakt ősosztály. A játékban szereplő kasztok irányítását valósítja
 * meg. Példánya lehet a szerelő (Mechanic) illetve a szabotőr (Saboteur).
 */
public abstract class Player {
    /**
     * Attribútumok:
     * -element: az a mező, amin a játékos áll.
     */
    protected Element element;

    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public abstract String toString();

    /**
     * Az objektumon meghívódik ez a függvény, ha a játékos egy
     * másik mezőre szeretne lépni. A függvény meghívja a játékos jelenlegi mezőjén az
     * isNeighbour(e) függvényt, az új mezővel paraméterezve, és ha ez true-val tér vissza, akkor
     * meghívódik az e-n az AcceptPlayer(p) függvény, amivel a játékos odalép, illetve az eddigi
     * mezőn (element) meghívódik a RemovePlayer(p).
     * @param e az a mező, amire a játékos lépni szeretne
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void move(Element e, int depth) throws IOException {
        //0
        System.out.print("->move(" + e.toString() + ")\n");
        depth += 1;

        //1
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->isNeighbour(" + e + ")");
        element.isNeighbour(e);

        //Asks the user if the pipe is occupied
        if(e.toString().equals("pipe")){
            System.out.println("\nIs the " + e.toString() + " occupied? (Y/N)");
            while(true){
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String s = reader.readLine();
                if(s.equals("Y")) {
                    System.out.println("The "+e.toString()+" is already occupied.");
                    return;
                }else if(s.equals("N"))
                    break;
            }

        }
        System.out.print("\n");
        e.acceptPlayer(new Mechanic());
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->acceptPlayer(" + this.toString() + ")");
        element.removePlayer(this);
        System.out.print("\n");
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->removePlayer(" + this.toString() + ")");
        return;
    }

    /**
     * A játékos objektum interakcióját valósítja meg az adott
     * mezővel (Element) amin áll: szerelőnél (Mechanic) - cső és pumpa javítása (RepairElement()),
     * szabotőrnél (Saboteur) - cső kilyukasztása (breakElement()).
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public abstract void doElement(int depth);

    public void controlPump(PassiveElement p1, PassiveElement p2, int depth){
        //0
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->controlPump(" + p1.toString() + "," + p2.toString() + "2)\n");
        depth+= 1;
        //1
        ((Pump)this.element).setInPipe(p1,depth);
        //2
        ((Pump)this.element).setOutPipe(p2,depth);

    }

    public void setElement(Element e){

    }

}
