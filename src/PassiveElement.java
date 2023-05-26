import java.util.Random;

/**
 * A csoveket megvalosito osztaly
 * Ezek a palya "alkotoelemei", bennuk folyik a viz
 */
public class PassiveElement extends Element{
    private int id;
    private int capacity;
    private int load;
    protected ActiveElement e1;
    protected ActiveElement e2;
    private int protectTime;
    private int slipTime;
    private int stickTime;


    /**
     * A cső konstruktora
     */
    public PassiveElement(int _id, Game _game){
        super(_game);
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
        else if(e2 == null){
            e2 = p;
            e2.connectElement(this);
        }

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
        ActiveElement temp = e1;
        removeConnection(e1);
        String pid = game.getNewPipeId();
        game.addElement(pid, 0, 0);
        game.getPipe(pid).setConnection((ActiveElement) e);
        game.getPipe(pid).setConnection(temp);
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

    /**
     * Kiveszi a csőben lévő vizet
     * @return a csőben lévő viz
     */
    public int removeWater(){
        leak();
        int temp = load;
        load = 0;
        return load;
    }

    /**
     * beleteszi a csőbe a vizet
     * @param i a mennyiség, amit beletesz
     */
    public void addWater(int i){
        leak();
        load += i;
    }

    /**
     * megvizsgálja, hogy a csőből kifolyik-e a víz
     */
    public void leak() {
        if(e1 == null || e2 == null || broken){
            int points = load;
            load = 0;
            game.incrementSaboteurPoints(points);
        }
    }

    /**
     * megvizsgálja, hogy fdoglalt-e a cső
     * @return
     */
    public boolean occupied(){
        return players.size() == 0;
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

    /**
     Visszaadja az elem jelenlegi terhelését.
     @return az elem jelenlegi terhelése
     */
    public int getLoad(){
        return load;
    }
    /**

     Meghatározza, hogy az elemhez csatlakozik-e két aktív elem.
     @return true, ha mindkét oldalán van aktív elem, false egyébként
     */
    public boolean connected(){
        return e1 != null && e2 != null;
    }
    /**

     Az elemet megjavítja, ha az tönkrement.
     Ha az elem tönkrement, akkor az ismételten működőképessé válik, és 10 másodperces védettséget kap.
     */
    public void repairElement(){
        if(broken) {
            broken = false;
            protectTime = 10;
        }
    }
    /**

     Az elemhez csatlakozó játékost elfogadja.
     Ha az elemen csúszás van, akkor áthelyezi a játékost a véletlenszerűen választott elem másik végére.
     Ha az elemen ragacs van, akkor a játékos ragadós lesz.
     @param p az elfogadandó játékos
     */
    public void acceptPlayer(Player p){
        if(slipTime > 0){
            slip(p);
            return;
        }
        else if(stickTime > 0){
            stick(p);
        }
        p.setElement(this);
        players.add(p);
    }
    /**

     * Visszaadja az elem egyedi azonosítóját.
     * @return az elem egyedi azonosítója
     */
    public int getId(){
        return id;
    }
    /**

     * Beállítja az elemre vonatkozó ragadás időtartamát.
     * @param n a beállítandó ragadás időtartama
     */
    public void setStickTime(int n){
        stickTime = n;
    }
    /**

     * Beállítja az elemre vonatkozó csúszás időtartamát.
     * @param n a beállítandó csúszás időtartama
     */
    public void setSlipTime(int n){
        slipTime = n;
    }
    /**

     * Beállítja az elem védettségi idejét.
     * @param n a beállítandó védettségi időtartam
     */
    public void setProtectTime(int n){
        protectTime = n;
    }
    /**

     * Áthelyezi a játékost az elem másik végére.
     * @param p a csúszni kívánó játékos
     */
    public void slip(Player p){
        Element e = this.randomEnd();
        e.acceptPlayer(p);
        p.setElement(e);
    }
    /**
     * A játékost ragadóssá teszi az elemen.
     * @param p a ragadóssá tenni kívánt játékos
     */
    public void stick(Player p){
        p.setStuck(true);
    }
    /**

     * Visszaadja a cső két végpontja közül véletlenszerűen az egyiket.
     * @return a cső véletlenszerűen kiválasztott egyik végpontja
     */
    public Element randomEnd(){
        Random r = new Random();
        int end = r.nextInt(0, 1);
        if(end == 0)
            return e1;
        else
            return e2;
    }
    /**

     * Visszaadja az elem egyik végpontját.
     * @return az elem egyik végpontja
     */
    public ActiveElement getE1(){
        return e1;
    }
    /**

     * Visszaadja az elem másik végpontját.
     * @return az elem másik végpontja
     */
    public ActiveElement getE2(){
        return e2;
    }
    /**

     * Visszaadja az időt, amíg a játékos ragadós anyaggal lett bevonva.
     * @return az idő, amíg a játékos ragadós anyaggal lett bevonva
     */
    public int getStickTime(){
        return stickTime;
    }
    /**

     * Visszaadja az időt, amíg a játékos csúszós anyaggal lett bevonva.
     * @return az idő, amíg a játékos csúszós anyaggal lett bevonva
     */
    public int getSlipTime(){
        return slipTime;
    }
    /**

     * Visszaadja az időt, amíg az elem védve van.
     * @return az idő, amíg az elem védve van
     */
    public int getProtectTime(){
        return protectTime;
    }

    /** A Skeleton teszt programhoz keszitett override-olt toString metodus
     * @return az adott objektum tipusat adja vissza String-kent
     */
    @Override
    public String toString() {
        return "pi_" + id;
    }


}