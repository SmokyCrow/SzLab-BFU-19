/**
 * A Pumpa típusú játék mezőt modellező osztály.
 */

public class Pump extends ActiveElement{



    @Override
    public String toString() { return "pump";}

    /**
     * Beállítja a befolyó csövet (inPipe) a paraméterül kapottra.
     * @param p azon cső mező, amelyből a víz folyni fog a pumpába
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void setInPipe(PassiveElement p, int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->setInPipe(" + p.toString() + ")");
    }

    /**
     * Beállítja a kifolyó csövet (outPipe) a paraméterül kapottra.
     * @param p azon cső mező, amelyből a víz folyni fog a pumpába
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void setOutPipe(PassiveElement p, int depth){
        System.out.print("\n");
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->setOutPipe(" + p.toString() + "2)");
    }

    /**
     * Visszaadja, hogy a paraméterként átadott element leszedhető-e a pumpáról.
     * A skeletonban még nem lényeges, a visszatérési érték mindig "igaz".
     */
    public boolean giveElementEnd(Element e){
        return true;
    }

    /**
     * Csatlakoztatja a paraméterként kapott elementet a pumpára, ha az eddig nem volt rákötve.
     * A skeletonban még nem lényeges, a visszatérési érték mindig "igaz".
     */
    public boolean connectElement(Element e) {
        return true;
    }

}
