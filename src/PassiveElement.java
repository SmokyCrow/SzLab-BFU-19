/**
 * A csoveket megvalosito osztaly
 * Ezek a palya "alkotoelemei", bennuk folyik a viz
 */
public class PassiveElement extends Element{
    /** Tagvaltozo, a cso egyik vegebe kotott pumpa
     */
    private Pump pump1;
    /** Tagvaltozo,  cso masik vegebe kotott pumpa
     */
    private Pump pump2;

    /** Egy pumpaval valo kapcsolodast allitja be
     * @param p a beallitani kivant pumpa
     */
    public void setConnection(Pump p){
    }

    /** Egy pumpaval valo kapcsolodast tavolitja el
     * @param p az eltavolitando pumpa
     */
    public void removeConnection(Pump p){
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

    /** A Skeleton teszt programhoz keszitett override-olt toString metodus
     * @return az adott objektum tipusat adja vissza String-kent
     */
    @Override
    public String toString() {
        return "pipe";
    }
}