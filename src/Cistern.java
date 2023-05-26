import java.nio.channels.Pipe;

/**
 * A Ciszterna típusú játékmezőt modellező osztály. A ciszternába vezet a csőhálózat, méri a
 * befolyt vizet, itt készülnek új pumpák és csövek is.
 */
public class Cistern extends ActiveElement{
    private int id;

    /**
     * A ciszterna konstruktora
     * @param _id az elem azonosítója
     * @param _game a játék referenciája
     */
    public Cistern(int _id, Game _game){
        super(_game);
        id = _id;
    }

    /**
     * A víz áramlását szimulálja az összes csőelemen keresztül,
     * és meghívja a megfelelő metódust a mechanikus pontszám frissítéséhez.
     */
    public void moveWater(){
        for(int i = 0; i < pipes.size(); i++){
            int points = pipes.get(i).removeWater();
            game.incrementMechanicPoints(points);
        }
    }

    /**
     * Új pumpát hoz létre, hozzáadja a játékhoz és visszaadja az új elemet.
     * @return a létrehozott pumpa elem
     */
    public Element giveElement(){
        String id = game.getNewPumpId();
        game.addElement(id, 0, 0);
        return game.getPump(id);
    }



    /**
     * Az objektumok típusának kiírásához használt felüldefiniált függvény
     * @return az objektum típusa
     */
    @Override
    public String toString() { return "ci_" + id;}

    /**
     * Visszaadja, hogy sikeresen fel lehet-e venni egy új csövet vagy sem.
     * @param e az ellenőrizendő mező
     */
    public boolean giveElementEnd(Element e){
        if(((PassiveElement) e).getE1() == null || ((PassiveElement) e).getE2() == null)
            return true;
        return false;
    }

    /**
     * Beköt egy csövet a ciszternába. miután ellenőrizte, hogy az adott cső nincs-e már bekötve.
     * @param e a ciszternába bekötendő cső mező elem.
     * @return mindig true a skeletonbaqn.
     */
    public boolean connectElement(Element e){
        if(!isNeighbour(e)){
            pipes.add((PassiveElement) e);
            return true;
        }
        return false;
    }

    /**
     * Lecsatol egy csövet a ciszternáról
     * @param p a cső, amelyet le szeretnénk kapcsolni
     */
    public void disconnectPipe(PassiveElement p) {
        pipes.remove(p);
    }

    /**
     * becsatol egy csövet a ciszternához
     * @param p a becsatolni kívánt cső
     */
    public void addPipe(PassiveElement p){
        pipes.add(p);
    }

}
