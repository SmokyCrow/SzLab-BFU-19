/**
 * A játékmezőket leíró absztrakt osztály. Egy mezőt reprezentál. Példánya lehet például egy cső,
 * vagy valamilyen nem cső típusú mező.
 */
public abstract class Element {

    /**
     * Attribútumok:
     * -Player players: A játékmező tárolja a rajta álló játékosokat.
     * -boolean broken: A játékmező tudja, ha elromlott/lyukas.
     */
    private Player[] players;
    private boolean broken = false;

    /**
     * Beállítja a broken attribútum értékét
     * @param b a beállítandó boolean érték
     */
    public void setBroken(boolean b){ this.broken = b;}

    /**
     * A függvény meghívásakor a mező átállítja a broken
     * attribútumát false-ra.
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void repairElement(int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->repairElement()");
    }

    /**
     * Itt még nem csinál semmit, a Pipe-ban lesz megvalósítva (és
     * részletezve).
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     */
    public void breakElement(int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.print("->breakElement()");
    }

    /**
     * Egy elem játékmező (Element) elhelyezését
     * megvalósító függvény. A leszármazottak felüldefiniálják, itt még csak false-al tér vissza.
     * @param e a letenni kívánt elem
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     * @return false
     */
    public boolean placeElement(Element e, int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.println("->placeElement()");
        return false;
    }



    /**
     * Egy elementet ad vissza, ebben az osztályban még
     * “null”-al tér vissza. Felüldefiniálva a Cistern-nél van csak.
     * @param depth megadja, hogy a függvény milyen mélyen található a hívási listában
     * @return null
     */
    public Element giveElement(int depth){
        for(int i = 0; i < depth; i++){
            System.out.print("    ");
        }
        System.out.println("->giveElement()");
        return null;
    }

    /**
     * Itt még csak false-al tér vissza, a Cistern és a
     * Pump-ban lesz megvalósítva (és részletezve).
     * @param e az átvenni kívnt véghez tartozó elem
     * @return false
     */
    public boolean giveElementEnd(Element e){
        return false;
    }

    /**
     * A függvény meghívásakor a játékosmező eltárolja a
     * rálépő játékost a players tömbben.
     * @param p a mezőre lépő játékos
     */
    public void acceptPlayer(Player p){

    }

    /**
     * A függvény meghívásakor a játékosmező kitörli a
     * mezőt elhagyó játékost a players-ből.
     * @param p a mezőről eltávolíandó játékos
     */
    public void removePlayer(Player p){

    }

    /**
     * Megnézi, hogy a paraméterben kapott mező
     * szomszédos-e a jelenlegi mezővel, ha igen: true, ha nem: false a visszatérítési érték.
     * @param e a megvizsgálandó mező
     * @return szomszédosas-e a mezők
     */
    public abstract boolean isNeighbour(Element e);

    /**
     * Az objektumok típusának kiírásához használt felüldefiniált föggvény
     * @return az objektum típusa
     */
    public abstract String toString();
}
