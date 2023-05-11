import java.nio.channels.Pipe;

public class Cistern extends ActiveElement{
    private int id;

    public Cistern(int _id){
        id = _id;
    }

    public void moveWater(){
        for(int i = 0; i < pipes.size(); i++){
            int points = pipes.get(i).removeWater();
            game.incrementMechanicPoints(points);
        }
    }

    public Element giveElement(){
        //return new Pump(game.getPumpList().size() + 1);
        return null;
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
        return true;
    }

    /**
     * Beköt egy csövet a ciszternába. miután ellenőrizte, hogy az adott cső nincs-e már bekötve.
     * @param e a ciszternába bekötendő cső mező elem.
     * @return mindig true a skeletonbaqn.
     */
    public boolean connectElement(Element e){
        for(int i = 0; i < pipes.size(); i++){
            if(pipes.get(i).getId() == ((PassiveElement)e).getId())
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
