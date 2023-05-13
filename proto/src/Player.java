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
    private boolean stuck = false;

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
     */
    public void move(Element e) throws Exception {
        if(!stuck){
            if(element.isNeighbour(e)){
                element.removePlayer(this);
                e.acceptPlayer(this);
            }
            else
                throw new Exception("Rossz mozgas!");
        }
    }

    public void punchHole(){
        element.breakElement();
    }

    /**
     *A metódus hívásával a játékos objektum egy pumpán átállítja a vizet szállító csöveket.
     * A függvény a jelenlegi mezőn (element) meghívja annak setInPipe(p1) és setInPipe(p2)
     * függvényeit ezzel beállítva a két új aktív csövet a paraméterben kapottakra(p1, p2).
     * @param p1 az egyik működésbe hozandó cső (Pipe) mező
     * @param p2 az egyik működésbe hozandó cső (Pipe) mező
     */
    public void controlPump(PassiveElement p1, PassiveElement p2){
        ((Pump)element).setInPipe(p1);
        ((Pump)element).setOutPipe(p2);
    }

    public void makeSticky(){
        ((PassiveElement)element).setStickTime(10);
    }

    public void setStuck(boolean b){
        stuck = b;
    }

    public boolean getStuck(){
        return stuck;
    }

    public void setElement(Element e){
        element = e;
    }
}
