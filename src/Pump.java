/**
 * A Pumpa típusú játék mezőt modellező osztály.
 */

public class Pump extends ActiveElement{
    private int id;

    public Pump(int _id){
        id = _id;
    }


    @Override
    public String toString() { return "pu_" + id;}

    /**
     * Beállítja a befolyó csövet (inPipe) a paraméterül kapottra.
     *
     * @param p azon cső mező, amelyből a víz folyni fog a pumpába
     */
    public void setInPipe(PassiveElement p){

    }

    /**
     * Beállítja a kifolyó csövet (outPipe) a paraméterül kapottra.
     *
     * @param p azon cső mező, amelyből a víz folyni fog a pumpába
     */
    public void setOutPipe(PassiveElement p){

    }

    /**
     * Visszaadja, hogy a paraméterként átadott element leszedhető-e a pumpáról.
     * A skeletonban még nem lényeges, a visszatérési érték mindig "igaz".
     */
    public boolean giveElementEnd(Element e){
        return true;
    }

    public int getId(){
        return id;
    }

    /**
     * Csatlakoztatja a paraméterként kapott elementet a pumpára, ha az eddig nem volt rákötve.
     * A skeletonban még nem lényeges, a visszatérési érték mindig "igaz".
     */
    public boolean connectElement(Element e) {
        return true;
    }

}
