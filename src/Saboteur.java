/**
 * A szabotőr szerepkört megvalósító osztály, ami a játékos által irányított karakterek egyike.
 */
public class Saboteur extends Player{

    /**
     * Az objektum a jelenlegi mezővel (element) interaktál, a függvény
     * meghívja a breakElement() függvényt a mezőn (element).
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void doElement(int depth){ //0
        System.out.println("->doElement()");
        depth +=1;
        element.breakElement(depth); //1
    }

    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public String toString() { return "saboteur";}

}
