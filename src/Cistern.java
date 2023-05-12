import java.nio.channels.Pipe;

public class Cistern extends ActiveElement{
    private int id;

    public Cistern(int _id, Game _game){
        super(_game);
        id = _id;
    }

    public void moveWater(){
        for(int i = 0; i < pipes.size(); i++){
            int points = pipes.get(i).removeWater();
            game.incrementMechanicPoints(points);
        }
    }

    public Element giveElement(){
        String id = game.getNewPumpId();
        game.addElement(id);
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
        pipes.remove(e);
        return true;
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

    public void disconnectPipe(PassiveElement p) {
        pipes.remove(p);
    }

    public void addPipe(PassiveElement p){
        pipes.add(p);
    }

}
