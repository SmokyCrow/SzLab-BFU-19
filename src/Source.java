/**
 * A Forras tipusu jatekmezot modellezo osztaly
 * Innen ered a viz a csorendszerbe, kulonosebb felelossege nincs
 */
public class Source extends ActiveElement{
    private int id;
    /**
     * A Source osztály konstruktora.
     * @param _id az azonosító, amelyet a forrásnak beállítunk
     * @param _game a játék objektum, amelyben a forrás van
     */
    public Source(int _id, Game _game){
        super(_game);
        id = _id;
    }
    /**
     * A metódus, amely az első Pipe objektumra csatlakozó PassiveElement objektumra
     * bocsát ki 1 egység vizet, ha az üres.
     */
    public void moveWater(){
        PassiveElement outP = pipes.get(0);
        if(outP.getLoad() == 0){
            outP.addWater(1);
        }
    }
    /**

    * A metódus, amely hozzáadja a kapott elemet a forráshoz.
    * @param e az Element objektum, amelyet hozzáadunk a forráshoz
    * @return true, ha a kapott elemet sikeresen hozzáadtuk a forráshoz,
    * false, ha a kapott elem már a forrás szomszédja volt.
    */
    public boolean connectElement(Element e){
        if(!isNeighbour(e)){
            pipes.add((PassiveElement) e);
            return true;
        }
        return false;
    }
    /**
     *  Az osztály toString metódusa, amely azonosítóval tér vissza.
     *  @return az osztály azonosítója String formában
     */
    @Override
    public String toString() { return "so_" + id;}

    /**
     * Az osztály getId metódusa, amely visszaadja az osztály azonosítóját.
     * @return az osztály azonosítója
     */
    public int getId(){
        return id;
    }
}
