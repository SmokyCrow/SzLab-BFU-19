/**
 * A csoveket megvalosito osztaly
 * Ezek a palya "alkotoelemei", bennuk folyik a viz
 */
public class PassiveElement extends Element{
    private int id;
    private int capacity;
    private int load;
    private Element e1;
    private Element e2;
    private int protectTime;
    private int slipTime;
    private int stickTime;



    public PassiveElement(Element _e1, Element _e2, int _id){
        e1 = _e1;
        e2 = _e2;
        id = _id;
    }

    /** Egy pumpaval valo kapcsolodast allitja be
     * @param p a beallitani kivant pumpa
     */
    public void setConnection(Pump p){
        if(e1 == null)
            e1 = p;
        else if(e2 == null)
            e2 = p;
    }

    /** Egy pumpaval valo kapcsolodast tavolitja el
     * @param p az eltavolitando pumpa
     */
    public void removeConnection(Pump p){
        if(((Pump)e1).getId() == p.getId())
            e1 = null;
        else if(((Pump)e2).getId() == p.getId())
            e2 = null;
    }

    /** Egy uj pumpa lerakasa
     * @param e Element, amire le szeretnenk rakni a pumpat
     * @return true vagy false, attol fuggoen, hogy siikerult-e a pumpa lerakasa
     */
    public boolean placeElement(Element e){
        return true;
    }

    /** Megadja, hogy a parameterkent kapott Element szomszedja-e a csonek
     * @param e a vizsgalando Element
     * @return true vagy false, attol fuggoen, hogy szomszedja-e vagy sem
     */
    public boolean isNeighbour(Element e) {
        return false;
    }

    /** A csovet viz befogadasara alkalmatlanna teszi
     * Meghivasakor a metodus beallitja a broken tagvaltozo
     * erteket "true"-ra
     */
    public void breakElement(){

    }

    /** Megadja, hogy a cso eppen foglalt-e
     * @return ha foglalt "true-val" ter vissza, ha ures, akkor "false"-al
     */
    public boolean occupied(){
        return true;
    }

    /** Megadja, hogy a cso mindket vege be van-e kotve egy Element-be
     * @return Ha az objektum pipe1 és pipe2 tagvaltozoja nem null, akkor "true"-val ter vissza
     * ellenkezo esetben "false"-al
     */
    public boolean connected(){
        return true;
    }

    public int getId(){
        return id;
    }

    public void setStickTime(int n){
        stickTime = n;
    }

    public void setSlipTime(int n){
        slipTime = n;
    }

    public int removeWater(){
        int temp = load;
        load = 0;
        return load;
    }

    /** A Skeleton teszt programhoz keszitett override-olt toString metodus
     * @return az adott objektum tipusat adja vissza String-kent
     */
    @Override
    public String toString() {
        return "pi_" + id;
    }

    public int leak() {
        int points = removeWater();
        if(e1 == null || e2 == null || broken){
            game.incrementSaboteurPoints(points);
            return 0;
        }
        return points;
    }
}