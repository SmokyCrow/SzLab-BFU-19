import java.util.ArrayList;

/**
 * A nem cső típusú játékmezőket modellező ősosztály, leszármazottai a Source,
 * Cistern, Pump osztályok.
 */
public class ActiveElement extends Element{
    protected int waterInside;
    protected ArrayList<PassiveElement> pipes = new ArrayList<>();

    /**
     * Az osztály konstruktora.
     * @param _game az aktuális játék példánya
     */
    public ActiveElement(Game _game) {
        super(_game);
    }

    /**
     * A játék folyamatában a játékmezők esetében hívódik meg a függvény, amelyik
     * eltávolítja a játékmezőből a vízmennyiséget, és az azt meghívó játékos számára
     * a vízmozgásból adódó pontokat hozzáadja a játékos pontszámához.
     */
    public void breakRandom(){ }

    /**
     * Az aktív elemeknek nincs egyedi azonosítójuk, ezért az alapértelmezett értéket
     * adjuk vissza.
     * @return az alapértelmezett érték, -1
     */
    public int getId(){
        return -1;
    }

    /**
     * Az aktív elemek nem köthetők össze más játékmezőkkel.
     * @param e a játékmező, amelyet össze akarunk kötni az aktív elemmel
     * @return mindig false
     */
    public boolean connectElement(Element e){
        return false;
    }

    /**
     * Az aktív elemek nem kapcsolódnak le csövekkel.
     * @param p a cső, amelyet le szeretnénk kapcsolni
     */
    public void disconnectPipe(PassiveElement p){

    }

    /**
     * Ellenőrzi, hogy az adott elem szomszédja-e a jelenlegi elemnek.
     * @param e a vizsgálandó elem
     * @return true, ha a vizsgálandó elem a jelenlegi elem szomszédja, false egyébként
     */
    public boolean isNeighbour(Element e){
        for (PassiveElement pipe : pipes) {
            if (e.toString().startsWith("pi") && pipe.equals((PassiveElement)e) && !((PassiveElement)e).occupied())
                return true;
        }
        return false;
    }

    /**
     * Visszaadja az aktív elem belsejében található vízmennyiséget.
     * @return az aktív elem belsejében található vízmennyiség
     */
    public int getWaterInside(){
        return waterInside;
    }

    /**
     * Beállítja az aktív elem belsejében található vízmennyiséget.
     * @param n a beállítandó vízmennyiség
    */
    public void setWaterInside(int n){
        waterInside = n;
    }

    /**
     * Visszaadja az elembe bekötött csöveket.
     * @return az elemhez kapcsolódó csövek listája
     */
    public ArrayList<PassiveElement> getPipes(){
        return pipes;
    }



    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public String toString() {
        return "activeElement";
    }
}
