import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A szerelő szerepkört megvalósító osztály, ami a játékos által irányított karakterek egyike.
 */
public class Mechanic extends Player{
    private final int id;

    public Mechanic(int _id){
        id = _id;
    }

    /**
     * Attribútumok
     * -PassiveElement newPipe: az a cső mező, amelynek egyik végét a szerelő felvesz és
     * lerak egy másik pumpánál.
     * -Pump newPump: megmutatja, hogy van-e a szerelőnél pumpa: ha true- van, ha false - nincs
     */
    private Pump newPump = null;
    private PassiveElement newPipe = null;


    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public String toString() { return "m_" + id;}

    /**
     * A szerelő megjavítja a pumpát(Pump), illetve a csövet (Pipe). A
     * függvény meghívja a jelenlegi mezőre (element) a repairElement() függvényt.
     */
    public void repair() {
        element.repairElement();
    }

    /**
     * A szerelő felvesz egy új pumpát (Pump) a ciszternánál. A függvény
     * a giveElement() függvényt hívja meg a mezőn (element), és felveszi a newPump-ba a kapott
     * pumpát (Pump), ha jó helyen áll.
     */
    public void getPump(){
        if(newPump == null)
            newPump = (Pump)element.giveElement();
    }

    /**
     * A szerelő felvesz egy csövet (Pipe). A
     * függvény a giveElementEnd(p) függvényt hívja meg a mezőn (element), és a newPipe
     * attribútumába kapja meg a felvett csövet, ha jó helyen áll.
     * @param p a felvenni kívánt cső
     */
    public void pickUpPipe(PassiveElement p) {
        if(newPipe == null){
           if(element.giveElementEnd(p))
               newPipe = p;
        }
    }

    /**
     * A szerelő beköti egy pumpába (Pump) a csövet (newPipe).A
     * függvény a jelenlegi mezőn (element) meghívja a placeElement(newPipe)-ot és ha az true-val
     * tér vissza, akkor törli a newPipe-ból a csövet (Pipe).
     */
    public void placePipe() {
        newPipe.setConnection((Pump)element);
        if(element.isNeighbour(newPipe))
            newPipe = null;
    }

    /**
     * Meghívja a jelenlegi mezőn (element), ha van a szerelőnél pumpa
     * (newPump != null) a placeElement()-et, ha az true-val tér vissza, az elhelyezés sikeres volt,
     * eldobja a newPump-ot magától.
     */
    public void placePump() {
        if(element.placeElement(newPump)){
            newPump = null;
        }
    }
}
