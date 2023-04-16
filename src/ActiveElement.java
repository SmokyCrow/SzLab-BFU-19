import java.util.ArrayList;

/**
 A nem cső (PassiveElement) típusú játékmezőket modellező ősosztály, leszármazottai a Source,
 Cistern, Pump osztályok.
 */

public class ActiveElement extends Element{


    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    @Override
    public String toString() {
        return "pipe";
    }


    /**
     * Megnézi, hogy a paraméterben kapott mező szomszédos-e a jelenlegi mezővel.
     * @param e a megvizsgálandó mező
     * @return boolean, mindig "true" a skeletonban
     */
    public boolean isNeighbour(Element e){
        return true;
    }


}
