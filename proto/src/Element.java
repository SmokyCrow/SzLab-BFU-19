import java.util.ArrayList;

/**
 * A játékmezőket leíró absztrakt osztály. Egy mezőt reprezentál. Példánya lehet például egy cső,
 * vagy valamilyen nem cső típusú mező.
 */
public abstract class Element {

    public Element(Game _game){
        game = _game;
    }
    /**
     * Attribútumok:
     * -Player players: A játékmező tárolja a rajta álló játékosokat.
     * -boolean broken: A játékmező tudja, ha elromlott/lyukas.
     */
    protected ArrayList<Player> players = new ArrayList<>();
    protected boolean broken = false;
    protected Game game;

    /**
     * Beállítja a broken attribútum értékét
     * @param b a beállítandó boolean érték
     */
    public void setBroken(boolean b){ this.broken = b;}

    /**
     * A függvény meghívásakor a mező átállítja a broken
     * attribútumát false-ra.
     */
    public void repairElement(){
        broken = false;
    }

    /**
     * Itt még nem csinál semmit, a Pipe-ban lesz megvalósítva (és
     * részletezve).
     */
    public void breakElement(){}

    /**
     * Egy elem játékmező (Element) elhelyezését
     * megvalósító függvény. A leszármazottak felüldefiniálják, itt még csak false-al tér vissza.
     *
     * @param e a letenni kívánt elem
     * @return most még false-al tér vissza mindig, később nem így lesz
     */
    public boolean placeElement(Element e){
        return false;
    }

    /**
     * Egy elementet ad vissza, ebben az osztályban még
     * “null”-al tér vissza. Felüldefiniálva a Cistern-nél van csak.
     *
     * @return itt még null-al tér vissza mindig, később ez nem így lesz
     */
    public Element giveElement(){ return null; }

    /**
     * Itt még csak false-al tér vissza, a Cistern és a
     * Pump-ban lesz megvalósítva (és részletezve).
     * @param e az átvenni kívnt véghez tartozó elem
     * @return a skeletonban mindig false
     */
    public boolean giveElementEnd(Element e){ return false; }

    /**
     * A függvény meghívásakor a játékosmező eltárolja a
     * rálépő játékost a players tömbben.
     * @param p a mezőre lépő játékos
     */
    public void acceptPlayer(Player p){
        p.setElement(this);
        players.add(p);
    }

    /**
     * A függvény meghívásakor a játékosmező kitörli a
     * mezőt elhagyó játékost a players-ből.
     * @param p a mezőről eltávolíandó játékos
     */
    public void removePlayer(Player p){ }

    /**
     * Megnézi, hogy a paraméterben kapott mező
     * szomszédos-e a jelenlegi mezővel, ha igen: true, ha nem: false a visszatérítési érték.
     * @param e a megvizsgálandó mező
     * @return szomszédosas-e a mezők
     */
    public abstract boolean isNeighbour(Element e);

    public void moveWater(){ }

    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    public abstract String toString();

    public int getId(){
        return -1;
    }

}
