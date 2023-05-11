/**
 * A Pumpa típusú játék mezőt modellező osztály.
 */

public class Pump extends ActiveElement{
    private int id;
    private PassiveElement inPipe;
    private PassiveElement outPipe;

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
        boolean n = false;
        for (PassiveElement pi: pipes) {
            if(pi.getId() == p.getId()) {
                n = true;
                break;
            }
        }
        if(n && p.getId() != outPipe.getId())
            inPipe = p;
    }

    /**
     * Beállítja a kifolyó csövet (outPipe) a paraméterül kapottra.
     *
     * @param p azon cső mező, amelyből a víz folyni fog a pumpába
     */
    public void setOutPipe(PassiveElement p){
        boolean n = false;
        for (PassiveElement pi: pipes) {
            if(pi.getId() == p.getId()) {
                n = true;
                break;
            }
        }
        if(n && p.getId() != inPipe.getId())
            outPipe = p;
    }

    /**
     * A víz mozgatásáért felelős metódus
     * Csak akkor mozgat vizet, ha a pumpa nem broken
     * Ha a kimeneti cső tele van akkor nem tud beletenni több vizet
     */
    public void moveWater(){
        if(!broken) {
            int a = inPipe.removeWater();
            waterInside += a;
            if(outPipe.getLoad() == 0){
                outPipe.addWater(1);
                if(waterInside > 0)
                    waterInside -= 1;
            }
        }
    }

    /**
     * Visszaadja, hogy a paraméterként átadott element leszedhető-e a pumpáról.
     * A skeletonban még nem lényeges, a visszatérési érték mindig "igaz".
     */
    public boolean giveElementEnd(Element e){
        if(isNeighbour(e)){
            ((PassiveElement) e).removeConnection(this);
            return true;
        }
        return false;
    }

    /**
     * Lecsatlakoztatja a paraméterként kapott csövet.
     */
    public void disconnectPipe(PassiveElement p) {
        pipes.remove(p);
    }

    public int getId(){
        return id;
    }

    /**
     * Csatlakoztatja a paraméterként kapott elementet a pumpára, ha az eddig nem volt rákötve.
     * A skeletonban még nem lényeges, a visszatérési érték mindig "igaz".
     */
    public boolean connectElement(Element e) {
        if(!isNeighbour(e)){
            pipes.add((PassiveElement) e);
            return true;
        }
        return false;
    }

    public void breakRandom(){
        broken = true;
    }

}
