/**
 * A csoveket megvalosito osztaly
 * Ezek a palya "alkotoelemei", bennuk folyik a viz
 */
public class PassiveElement extends Element{
    private int id;
    private int capacity;
    private int load;
    private ActiveElement e1;
    private ActiveElement e2;
    private int protectTime;
    private int slipTime;
    private int stickTime;


    /**
     * A cső konstruktora
     */
    public PassiveElement(int _id){
        id = _id;
    }

    /** Egy pumpaval valo kapcsolodast allitja be
     * @param p a beallitani kivant pumpa
     */
    public void setConnection(ActiveElement p){
        if(e1 == null) {
            e1 = p;
            e1.connectElement(this);
        }
        else if(e2 == null)
            e2 = p;
            e2.connectElement(this);
    }

    /** Egy pumpaval valo kapcsolodast tavolitja el
     * @param p az eltavolitando pumpa
     */
    public void removeConnection(ActiveElement p){
        if(e1.getId() == p.getId()) {
            e1.disconnectPipe(this);
            e1 = null;
        }
        else if(e2.getId() == p.getId()) {
            e2.disconnectPipe(this);
            e2 = null;
        }
    }

    /** Egy uj pumpa lerakasa
     * @param e Element, amire le szeretnenk rakni a pumpat
     * @return true vagy false, attol fuggoen, hogy siikerult-e a pumpa lerakasa
     */
    public boolean placeElement(Element e){
        removeConnection(e1);
        int pid = game.getPipeNumber();
        game.addPipe(pid);
        game.getPipe(pid).setConnection((ActiveElement) e);
        game.getPipe(pid).setConnection(e1);
        setConnection((ActiveElement) e);
        return true;
    }

    /** Megadja, hogy a parameterkent kapott Element szomszedja-e a csonek
     * @param e a vizsgalando Element
     * @return true vagy false, attol fuggoen, hogy szomszedja-e vagy sem
     */
    public boolean isNeighbour(Element e) {
        if(e1.getId() == e.getId())
            return true;
        if(e2.getId() == e.getId())
            return true;
        return false;
    }

    /** A csovet viz befogadasara alkalmatlanna teszi
     * Meghivasakor a metodus beallitja a broken tagvaltozo
     * erteket "true"-ra
     */
    public void breakElement(){
        if(protectTime == 0) {
            broken = true;
        }
    }

    public void repairElement(){
        if(broken) {
            broken = false;
            protectTime = 10;
        }
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
        leak();
        int temp = load;
        load = 0;
        return load;
    }

    public void addWater(int i){
        leak();
        load += i;
    }

    public int getLoad(){
        return load;
    }

    public void acceptPlayer(Player p){
        if(slipTime > 0){
            slip(p);
        }
        else if(stickTime > 0){
            stick(p);
            p.setElement(this);
            players.add(p);
        }
    }

    public void slip(Player p){

    }

    public void stick(Player p){
        p.setStuck(true);
    }

    /** A Skeleton teszt programhoz keszitett override-olt toString metodus
     * @return az adott objektum tipusat adja vissza String-kent
     */
    @Override
    public String toString() {
        return "pi_" + id;
    }

    public void leak() {
        if(e1 == null || e2 == null || broken){
            int points = load;
            load = 0;
            game.incrementSaboteurPoints(points);
        }
    }
}